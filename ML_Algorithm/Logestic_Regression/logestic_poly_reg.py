'''
Created on 2018年12月24日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from logestic_regression.logestic_reg_repre import Logestic_regression

from sklearn.model_selection import train_test_split

from logestic_regression.logestic_reg_repre import plot_decision_boundary

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import PolynomialFeatures

from sklearn.preprocessing import StandardScaler
 
np.random.seed(666)

def get_data():
    
    '''
    Poly_reg in logestic regression 
    '''
    
    X=np.random.normal(0,1,size=(200,2))
    
    y=np.array(X[:,0]**2+X[:,1]**2<1.5,dtype='int')# X[:,0]对应x, X[:,1]对应y，是否<1.5 区分0,1，形成一个bool向量
    
    #plt.scatter(X[y==0,0],X[y==0,1],alpha=0.5)
    
    #plt.scatter(X[y==1,0],X[y==1,1],alpha=0.5)
    
    #plt.show()
    
    return X,y
    
    
def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test
    
    

def use_linear_logestic_regression():
    
    X,y=get_data()
    
    #X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_reg=Logestic_regression()
    
    log_reg.fit(X,y)
    
    score=log_reg.score(X,y)
    
    print("simple logestic score=",score)  ##
    
    plot_decision_boundary(log_reg,axis=[-4,4,-4,4])
    
    plt.scatter(X[y==0,0],X[y==0,1])
    
    plt.scatter(X[y==1,0],X[y==1,1])
    
    plt.show()
    

def PolynomialLogesticRegression(degree):
    
    return Pipeline([('poly',PolynomialFeatures(degree=degree)),\
                     
                     ('stand',StandardScaler()),\
                     
                     ('loges',Logestic_regression())]) ## self-define  logestic_regression is ok  only abide by sklearn standard
    
    
    
    
def use_self_poly_loges():
    
    X,y=get_data()
    
    poly_log_reg=PolynomialLogesticRegression(degree=2)
    
    poly_log_reg.fit(X, y)
    
    score=poly_log_reg.score(X,y)
    
    print("score=",score)
    
    plot_decision_boundary(poly_log_reg, axis=[-4,4,-4,4])
    
    plt.scatter(X[y==0,0],X[y==0,1])
    
    plt.scatter(X[y==1,0],X[y==1,1])
    
    plt.show()
   
    
def main():
    
    use_linear_logestic_regression()
    
    use_self_poly_loges()

if __name__ == '__main__':
    
    main()