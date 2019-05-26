'''
Created on 2019年5月12日

@author: qiguangqin
'''

#import numpy as np

from sklearn.model_selection import train_test_split

from sklearn.datasets import load_boston

from sklearn.tree import DecisionTreeRegressor


#np.random.seed(666)

def get_data():
    
    boston=load_boston()
    
    X=boston.data
    
    y=boston.target
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def use_dec_reg(X,y):
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    dt_reg=DecisionTreeRegressor()
    
    dt_reg.fit(X_train,y_train)
    
    score_test=dt_reg.score(X_test,y_test)
    
    score_train=dt_reg.score(X_train,y_train)
    
    print("score_test=",score_test,"score_train=",score_train)


def main():
    
    X,y=get_data()
    
    use_dec_reg(X, y)

if __name__ == '__main__':
    main()