'''
Created on 2019年3月13日

@author: qiguangqin

'''
from kmeans_test import kmeans_get,get_data,distance

import numpy as np

import random

import matplotlib.pyplot as plt

from collections import Counter

import pandas as pd

random.seed(666)

def nearest_distance(data,centroids):
    
    min_dist=np.float('inf')
    
    m=np.shape(centroids)[0]
    
    for i in range(m):
        
        d=distance(data,centroids[i,])
        
        if min_dist>d:
            
            min_dist=d
    
    return min_dist

def get_centroid_new_k(data,k):
    
    
    m,n=data.shape
    '''
    
    initiate the centroids k,n
    '''
    centroids=np.zeros((k,n))
    '''
    randomly get first centroid
    '''
    
    index=np.random.randint(0,m)
    
    centroids[0,:]=np.copy(data[index])
    
    '''
    initiate_distance_seq,each sample 
    
    sample number=m 
    
    each one has the nearest distance to specified centroid
    '''
    d=np.array([0.0 for _ in range(m)])
    
    for i in range(1,k):
        
        sum=0.0
        
        '''
        find Each sample nearest D from centroids
        '''
        
        for j in range(m):
            
            d[j]=nearest_distance(data[j,:],centroids[0:i,])
            
            sum+=d[j]
            
        sum*=random.random()
        
        for j,di in enumerate(d):
            
            sum-=di
            
            if sum>0:
                
                continue
            
            centroids[i,:]=np.copy(data[j,:])
            
            break
        
    return centroids

def main():
    
    data,y=get_data()
    
    k=2
    
    centroids=get_centroid_new_k(data, k)
    
    centroids_new,subcenter=kmeans_get(data, k, centroids)
    
    print(centroids_new, Counter(subcenter[:,0]).most_common(k))
  
    plt.scatter(data[y==0,0],data[y==0,1],alpha=0.5)
    
    plt.scatter(data[y==1,0],data[y==1,1],alpha=0.5)
    
    plt.scatter(centroids_new[:,0],centroids_new[:,1],color='m',marker='+')
    
    plt.show()
    
if __name__ == '__main__':
    main()