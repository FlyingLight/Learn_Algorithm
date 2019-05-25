# -*- coding: UTF-8 -*-

'''
Created on 2018��8��27��

@author: qiguangqin
'''

import numpy as np

import sklearn.datasets as dt

def train_test_split(X,y,test_ratio=0.2,seed=5):
    
 
    assert X.shape[0]==y.shape[0], \
    " the dimension of X and y must be equal"
    
    assert 0<=test_ratio<=1, \
    " the test_ratio is in 0.2 in default,and value must between 0 and 1"
    
    if seed:
    
        np.random.seed(seed)
    
    shuffle_indexes=np.random.permutation(len(X))
    
    test_size=int (len(X)*test_ratio)
      
    test_indexes=shuffle_indexes[:test_size]
    
    train_indexes=shuffle_indexes[test_size:]
    
    X_train=X[train_indexes]
    
    y_train=y[train_indexes]
    
    X_test=X[test_indexes]
    
    y_test=y[test_indexes]
    
    return X_train,X_test,y_train,y_test

def main():
        
    iris=dt.load_iris()
        
    X=iris.data
        
    y=iris.target
        
    X_train,X_test,y_train,y_test=train_test_split(X, y)
        
    print(X_train.shape[0],y_train.shape[0])
    
    
if __name__=="__main__":
    
    main()

    
    
    
    
