'''
Created on 2019年3月5日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import load_iris

from collections import Counter

from sklearn.tree import DecisionTreeClassifier

from logestic_regression.logestic_reg_repre import plot_decision_boundary



def get_data():
    
    iris=load_iris()
    
    X=iris.data[:,2:]
    
    y=iris.target
    
    return X,y


def use_info_entropy(X,y):
    

    dt_clf=DecisionTreeClassifier(max_depth=2,criterion="entropy")
    
    dt_clf.fit(X,y)
    
    plot_decision_boundary(dt_clf, axis=[0.5,7.5,0,3])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    plt.scatter(X[y==2,0],X[y==2,1],alpha=0.5,color="g")
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5,color="r")
    
    plt.show()
    
    
def split(X,y,d,value):  # 在维度d上，对阈值value进行划分
    
    '''
    模拟使用信息熵进行划分
    '''
    
    index_a=(X[:,d]<=value)
    
    index_b=(X[:,d]>value)
    
    return X[index_a],X[index_b],y[index_a],y[index_b]

def entropy(y): # y值包含了数据分成哪些类别
    
    counter=Counter(y)
    
    res=0.0
    
    for num in counter.values():
        
        p=num/len(y)
        
        res+=-p*np.log(p)
        
    return res


def entropy1(p):
    
    return -p*np.log(p)-(1-p)*np.log(1-p)


def gini_factor(p):
    
    return 1-(p**2+(1-p)**2)


def try_split(X,y):
    
    baseEntropy=entropy(y)
    
    bestInfoGain=0.0
    
    #best_entropy=float('inf') # best_entropy 初始化为inf
    
    best_d,best_v=-1,-1  # 在哪一个维度，哪一个阈值上面进行划分
    
    for d in range(X.shape[1]):# 一共有shape[1]的维度
        
        sorted_index=np.argsort(X[:,d]) # 每两个样本，d维度上中间的值
        
        for i in range(1,len(X)):
            
            if X[sorted_index[i-1],d]!= X[sorted_index[i],d]:
                
                v=(X[sorted_index[i-1],d]+X[sorted_index[i],d])/2
                
                X_l,X_r,y_l,y_r=split(X,y,d,v)
                
                p_l, p_r = len(X_l) /len(X), len(X_r) / len(X)
                
                e=p_l*entropy(y_l)+p_r*entropy(y_r)
                
                info_gain=baseEntropy-e
                
                if info_gain>bestInfoGain:
                    
                    bestInfoGain,best_d,best_v=info_gain,d,v
    
    return bestInfoGain,best_d,best_v
                
    
       
def main():
    
    X,y=get_data()
    
    use_info_entropy(X,y)
    
    best_entropy1,best_d1,best_v1=try_split(X, y)
    
    
    print("best_entropy1=",best_entropy1,"best_d1=",best_d1,"best_v1=",best_v1)
    
    X1_l,X1_r,y1_l,y1_r=split(X,y,best_d1,best_v1)
    
    print(entropy(y1_l),entropy(y1_r),Counter(y1_l),Counter(y1_r))
    
    best_entropy2,best_d2,best_v2=try_split(X1_r, y1_r)
    
    
    print("best_entropy2=",best_entropy2,"best_d2=",best_d2,"best_v2=",best_v2)
    
    X2_l,X2_r,y2_l,y2_r=split(X1_r,y1_r,best_d2,best_v2)
    
    print(entropy(y2_l),entropy(y2_r),list(Counter(y2_l)),Counter(y2_r))
    
    '''
    p1=np.linspace(0.01,0.99,200)
    
    plt.plot(p1,entropy1(p1),alpha=0.6,color="r")
    
    plt.plot(p1,gini_factor(p1),alpha=0.6,color="b")
    '''
    plt.show()
   
if __name__ == '__main__':
    main()