#coding=utf-8

'''
Created on 2018��8��24��

@author: qiguangqin
'''

import numpy as np
from cmath import  sqrt
from collections import Counter
from test03.metrics import accuracy_score

class knn_classifier:
    
    " knn_classifier "

    def __init__(self,k):   
    
        assert k>=1,"k must be valid"
     
        self._X_train=None
        self._y_train=None
        self.k=k
    
    def fit(self,x_train,y_train):
    
        assert x_train.shape[0]==y_train.shape[0], \
        "x_train and y_train  must be equal"
    
        assert x_train.shape[0]>=self.k, \
        "x_train and k must be valid"
    
        self._X_train=x_train
    
        self._y_train=y_train
    
        return self

    def predict(self,X_predict):
    
        assert X_predict.shape[1] ==self._X_train.shape[1], \
        " the feature numbers must be equal"
    
        assert self._X_train is not None and self._y_train is not None, \
        "X_train and y_train must be not none "
    
        y_predict= [ self._predict(x_tr) for x_tr in X_predict]
    
        return np.array(y_predict) ## sklearn rule return np.array()

    def _predict(self,x_tr):
    
        distance=[ sqrt(np.sum((x_tr-_x_train)**2)) for _x_train in self._X_train]
    
        nearest=np.argsort(distance)
    
        top_ky=[ self._y_train[i] for i in nearest[:self.k]]
    
        votes=Counter(top_ky)
    
        return votes.most_common(1)[0][0]
    
    def score (self,X_test,y_test):
        
        y_test_predict=self.predict(X_test)
        
        score=accuracy_score(y_test, y_test_predict)
        
        return score
    
    def  __repr__(self):
        
        return "KNN(k=%d)"%self.k

