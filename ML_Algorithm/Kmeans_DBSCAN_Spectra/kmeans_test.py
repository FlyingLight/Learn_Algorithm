'''
Created on 2018年11月27日

@author: qiguangqin
'''
import numpy as np

#from sklearn.datasets import load_digits

import matplotlib.pyplot as plt

from sklearn.datasets import load_iris

from collections import Counter


np.random.seed(666)

def get_data():
    
    '''
    i=range(1,22,5)
    
    range_i=np.sort(np.tile(i,[1,3]))[0] ## np.tile 得到的是一个矩阵，所以要取值的0行元素
    
    data=np.array([ np.random.uniform(i,i+1,size=2) for i in range_i ])
    
    ''' 
    iris=load_iris()
    
    X=iris.data
    
    y=iris.target
    
    X1=np.vstack([X[y==0],X[y==1]])
    
    y1=np.hstack([y[y==0],y[y==1]])
    
    return X1,y1   
    

def randcent(data,k): ## data must be a matrix
    
    n=data.shape[1]
    
    centroid=np.zeros((k,n))
    
    for j in range(n):
        
        min_j=np.min(data[:,j])
        
        max_j=np.max(data[:,j])
        
        randj=max_j-min_j
        
        centroid[:,j]=min_j*np.ones(shape=k, dtype=float)+randj*np.random.random(size=k)
            
    return centroid


def kmeans_get(data,k,centroids):
    
    '''
    centroid 质心  k个(n 维)
    
    data 原始数据 m*n，data.shape[1]==centroid.shape[1]
    
    subcenter 迭代后，的质心 (m*2),每一个样本的归类[0],与centroid的之间距离[1]
    
    r 每一个分类中(一共k个 ，每一个中的样本数量)
    
    sum_z = data[k]h
    '''
    
    assert data.shape[1]==centroids.shape[1],"data and centroids must be same"
    
    m,n=np.shape(data)
    
    #print(centroids.shape)
    
    subcenter=np.zeros(shape=(m,2)) ## 初始化每一个样本所属的类别，以及到k个质心的距离
    
    #print(subcenter.shape)
    
    Change=True # 定义一个变量，是否需要重新计算质心
    
    while Change ==True:
        
        Change=False
        
        '''
                        找出min_index,类似于select_sort
        '''
        for i in range(m): ## 遍历m 个样本
            
            min_index=-1 ###初始化最小值,默认是第k个类别
            
            dis_min=np.inf  ### 每一个样本，到k个质心 距离初始最小值为inf
            
            for j in range(k):
                
                dis=distance(data[i,:],centroids[j,:])
    
                if dis<dis_min:
                
                    min_index=j
                    
                    dis_min=dis
                    
            if subcenter[i,0] !=min_index: ## subcenter 每一个样本点 归属的质心，通过
                
                Change=True
                
                subcenter[i,]=np.array([min_index,dis_min]) 
                
                
        for j in range(k):
            
            centroids[j,]=np.mean(data[subcenter[:,0]==j,:],axis=0)
                
                 
    return centroids,subcenter
           
def distance(data,centroids):
    
    #assert data.shape[1]==centroids.shape[1],"data and centroids must be same"
    
    dis=(data-centroids).T.dot(data-centroids)
    
    return np.sqrt(dis)
    
            
def main():   
    
    data,y=get_data()
    
    k=2
    
    '''
    print(centroids_init)
    
    #plt.scatter(data[:,0],data[:,1],marker='o',alpha=0.5)
    
    #centroids,subcenter=kmeans_get(data, k, centroids_init)
    
    #print(centroids,subcenter)
    
    #plt.show()
    
    '''
    centroids=randcent(data, k)
    
    centroids_new,subcenter=kmeans_get(data, k, centroids)
    
    print(centroids_new, Counter(subcenter[:,0]).most_common(2))
  
    plt.scatter(data[y==0,0],data[y==0,1],alpha=0.5)
    
    plt.scatter(data[y==1,0],data[y==1,1],alpha=0.5)
    
    plt.scatter(centroids_new[:,0],centroids_new[:,1],color='m',marker='+')
    
    plt.show()

if __name__ == '__main__':
    
    main()