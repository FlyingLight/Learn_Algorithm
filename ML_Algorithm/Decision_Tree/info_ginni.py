'''
Created on 2019年5月3日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import load_iris

from collections import Counter

from sklearn.tree import DecisionTreeClassifier

from matplotlib.colors import ListedColormap


def plot_decision_boundary(model,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=model.predict(X_new) 
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap)  

def get_data():
    
    iris=load_iris()
    
    X=iris.data[:,2:]
    
    y=iris.target
    
    return X,y

def use_info_ginni(X,y):
    
    dt_clf=DecisionTreeClassifier(max_depth=2,criterion="gini")
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[0.5,7.5,0,3])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5,color="g")
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5,color="r")
    
    plt.show()
    
    
def split(X,y,d,v):
    
    index_a=(X[:,d]<=v)
    
    index_b=(X[:,d]>v)
    
    return X[index_a],X[index_b],y[index_a],y[index_b]


def gini(y):
    
    counter=Counter(y)
    
    res=1.0
    
    for num in counter.values():
        
        p=num/len(y)
        
        res-=p**2
    
    return res


def try_split(X,y):
    
    best_gini=float('inf')
    
    best_d,best_v=-1,-1
    
    for d in range(X.shape[1]):
        
        sorted_index=np.argsort(X[:,d])
        
        for m in range(1,len(X)):
            
            if(X[sorted_index[m-1],d]!=X[sorted_index[m],d]):
                
                v=(X[sorted_index[m-1],d]+X[sorted_index[m],d])/2
                
                X_l,X_r,y_l,y_r=split(X, y, d, v)
                
                p_l,p_r=len(y_l)/len(y),len(y_r)/len(y)
                
                e=p_l*gini(y_l)+p_r*gini(y_r)
                
                if e<best_gini:
                    
                    best_d,best_v=d,v
                    
                    best_gini=e
                    
    return best_d,best_v,best_gini
    
    
    
def main():
    
    X,y=get_data()
    
    use_info_ginni(X, y)
    
    print(list(Counter(y).values())[0])
    
    best_d1,best_v1,best_gini1=try_split(X, y)
    
    print("best_d1=",best_d1,"best_v1=",best_v1,"best_Gini1=",best_gini1)
    
    X1_l,X1_r,y1_l,y1_r=split(X,y,best_d1,best_v1)
    
    print("Gini_y1_l=",gini(y1_l),"Gini_y1_r=",gini(y1_r))
    
    best_d2,best_v2,best_gini2=try_split(X1_r, y1_r)
    
    print("best_d2=",best_d2,"best_v2=",best_v2,"best_Gini2=",best_gini2)
    
    X2_l,X2_r,y2_l,y2_r=split(X1_r,y1_r,best_d2,best_v2)
    
    print("Gini_y2_l=",gini(y2_l),"Gini_y2_r=",gini(y2_r))

if __name__ == '__main__':
    main()