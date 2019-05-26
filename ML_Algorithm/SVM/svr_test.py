'''
Created on 2019年4月26日

@author: qiguangqin
'''

import numpy as np

#import matplotlib.pyplot as plt

from sklearn.datasets import load_boston

from sklearn.model_selection import train_test_split

from sklearn.model_selection import GridSearchCV

from sklearn.svm import LinearSVR

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import StandardScaler



def get_data():
    
    boston=load_boston()
    
    X=boston.data
    
    y=boston.target
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    return X_train,X_test,y_train,y_test


def standard_linear_svr(epsilon=0.1,C=1):
    
    return Pipeline([
        
        ("stand",StandardScaler()),\
        
        ("linear_Svr",LinearSVR(epsilon=epsilon,C=C))
        
        ])
    
    
def grid_search_svr(X_train,y_train):
    
    param_grid=[  {
                   
                   "epsilon":[i for i in np.linspace(0.01,100,50)],\
                   
                   "C":[i for i in np.linspace(0.01,100,50)]\
        
        }  ]
    
    
    knn_reg=standard_linear_svr()

    grid_search=GridSearchCV(grid_search_svr,verbose=1,cv=5) ##cv 把train 数据集合分成的份数
    
    grid_search.fit(X_train,y_train)
    
    print(grid_search.best_params_)

def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    lin_svr=standard_linear_svr()
    
    lin_svr.fit(X_train,y_train)
    
    score=lin_svr.score(X_test,y_test) ## 回归问题 默认是R2
    
    
    print("score=",score)
    

if __name__ == '__main__':
    pass