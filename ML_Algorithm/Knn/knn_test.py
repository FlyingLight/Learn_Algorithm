#coding=utf-8

'''
Created on 2018��8��22��

@author: qiguangqin
'''

import numpy as np
from math import  sqrt
from collections import Counter
from numpy import argsort


def knn_test(X_train,y_train,x,k):
    
    '''
    knn  不需要训练过程的算法  x 直接和 X_train 使用
    
    k近邻算法是没有模型的算法，和其他算法统一，训练数据集，就是模型本身
    
    '''
    
    assert X_train.shape[0]==y_train.shape[0],\
    "the size of X_train must be equal to y_train"
    assert X_train.shape[1]==x.shape[0],\
    "the feature number of x must be equal to X_train "
    assert 1<=k<=X_train.shape[0],"k must be valid"
    
    distance=[sqrt(np.sum((x_train-x)**2)) for x_train in X_train]
    
    '''
    get the distance between x and each vector of X_train
    '''
    nearest=argsort(distance) ### sort the distance from index 
    
    topK_y= [y_train[i] for i in nearest[:k]]
    '''
    get the k nearest y_train from index argsort(distance)
    '''
    return Counter(topK_y).most_common(1)[0][0]

def main():
    
    np.random.seed(20)
    X_train=np.random.normal(0,1,size=(4,4))
    y_train=np.random.randint(0,2,size=4)
    k=3
    x=np.array([0.5,0.2,0.8,0.6])
    y_predict=knn_test(X_train, y_train, x, k)
    
    print(y_predict)

if __name__=='__main__':
    
    main()
    
   
