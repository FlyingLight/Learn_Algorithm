'''
Created on 2019年5月8日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from collections import Counter

from sklearn.datasets import load_iris

from sklearn.datasets import make_moons

from sklearn.model_selection import train_test_split

from test03.metrics import accuracy_score

from matplotlib.colors import ListedColormap


class node:
    
    '''
    define the decision tree node
    '''
    
    def __init__(self,index=-1,value=None,results=None,right_child=None,left_child=None):
        
        self.index=index
        
        self.value=value
        
        self.results=results
        
        self.right_child=right_child
        
        self.left_child=left_child
        
        
   
def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=66)
    
    return X_train,X_test,y_train,y_test
        

def get_data():
    
    iris=load_iris()
    
    X=iris.data[:,2:]
    
    y=iris.target
    
    return X,y

    
def get_data2():
    
    X,y=make_moons(noise=0.25,random_state=666)
    
    return X,y


def gini(y):
    
    counter=Counter(y)
    
    res=1.0
    
    for num in counter.values():
        
        p=num/len(y)
        
        res-=p**2
        
    return res

def split(X,y,d,v):
    
    index_a=(X[:,d]<=v)
    
    index_b=(X[:,d]>v)
    
    return X[index_a],X[index_b],y[index_a],y[index_b]

    

def create_tree(X,y):
    
    '''
    max_sample_split
    
    if len(y)<9:
        
        return node(results=Counter(y).most_common()[0][0])
        
    '''
    
    if(len(y))==0:
        
        return node()
    
    current_Gini=gini(y)
    
    best_Gain=0.0
    
    bestCriteria=None
    
    bestSets=None
    
    for d in range(X.shape[1]):
        
        sorted_index=np.argsort(X[:,d])
        
        for m in range(1,len(X)):
            
            if(X[sorted_index[m-1],d]!=X[sorted_index[m],d]):
                
                v=(X[sorted_index[m-1],d]+X[sorted_index[m],d])/2
                
                X_l,X_r,y_l,y_r=split(X, y, d, v)
                
                p_l,p_r=len(y_l)/len(y),len(y_r)/len(y)
                
                new_Gini=p_l*gini(y_l)+p_r*gini(y_r)
                
                gain=current_Gini-new_Gini
                
                if gain>best_Gain and len(y_r)>0 and len(y_l)>0:
                    
                    best_Gain=gain
                    
                    bestCriteria=(d,v)
                    
                    bestSets=(X_l,X_r,y_l,y_r)
                
    #if best_Gain>0 and min(len(bestSets[3]),len(bestSets[2]))>2:
    
    if best_Gain>0 :    
        
        
        right_child=create_tree(bestSets[1], bestSets[3])
        
        left_child=create_tree(bestSets[0], bestSets[2])
        
        return node(index=bestCriteria[0],value=bestCriteria[1],right_child=right_child,left_child=left_child)
        
    else:
         
        return node(results=Counter(y).most_common()[0][0])
    
           
    
def predict(X, tree):   
    
    if tree.results!=None:
        
        return tree.results
    
    else:
        
        val_sample=X[tree.index]     
        
        branch=None
        
        if val_sample>=tree.value:
            
            branch=tree.right_child
        
        else:
            
            branch=tree.left_child
            
        return predict(X,branch)
            


def plot_decision_boundary(tree,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=np.array([ predict(x_new, tree) for x_new in X_new ])
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap)  
       
def main():
    
    
    
    X,y=get_data2()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    tree=create_tree(X_train, y_train)
    
    label={0:"blue",1:"green",2:"red"}
    
    #plot_decision_boundary(tree, axis=[0.5,7.5,0,3])
    
    plot_decision_boundary(tree, axis=[-1.5,2.5,-1.0,1.5])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5,color="g")
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5,color="r")
    
    plt.show()
    
    y_predict= np.array([ predict(x_test, tree) for x_test in X_test ] )
    
    results=np.array([label[i] for i in y_predict])
    
    score=accuracy_score(y_test,np.array(y_predict))
    
    print("y_predict=",np.array(y_predict),"score=",score)
    
    print("results=",results)
    

if __name__ == '__main__':
    main()
