'''
Created on 2019年5月12日

@author: qiguangqin
'''

import numpy as np

from sklearn.datasets import load_boston

from cmath import sqrt

from test03.metrics import r2_score

#from sklearn.neighbors import KNeighborsRegressor

from test03.train_test_split import train_test_split

def get_data():
    
    boston=load_boston()
    
    X=boston.data
    
    y=boston.target
    
    X1=X[y<50]
    
    y1=y[y<50]
    
    return X1,y1

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    return X_train,X_test,y_train,y_test

class knn_regressor:
    
    " knn_regressor "

    def __init__(self,k):   
    
        assert k>=1,"k must be valid"
     
        self._X_train=None
        
        self._y_train=None
        
        self.k=k
    
    def fit(self,X_train,y_train):
    
        assert X_train.shape[0]==y_train.shape[0], \
        "x_train and y_train  must be equal"
    
        assert X_train.shape[0]>=self.k, \
        "x_train and k must be valid"
    
        self._X_train=X_train
    
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
    
        top_ky=np.array([ self._y_train[i] for i in nearest[:self.k]])
    
        top_ky_mean=np.mean(top_ky)
    
        return top_ky_mean
    

    def score(self,X_test,y_test):
        
        y_pre=self.predict(X_test)
        
        score=r2_score(y_test,y_pre)
        
        return score


def main():
    
    X,y=get_data()
    
    k=5
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    knn_reg=knn_regressor(k)
    
    #knn_reg=KNeighborsRegressor(k)
    
    #stand1=standScaler()

    #stand1.fit(X_train)
    
    #stand1_X_train=stand1.transform(X_train)
    
    #stand1_X_test=stand1.transform(X_test)
    
    knn_reg.fit(X_train, y_train)
    
    score=knn_reg.score(X_test, y_test)
    
    print("score=",score)
        
if __name__ == '__main__':
    main()