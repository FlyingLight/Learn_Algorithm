'''
Created on 2019年1月14日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split

from sklearn.linear_model import LogisticRegression

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import StandardScaler

from sklearn.preprocessing import PolynomialFeatures

from logestic_regression.logestic_reg_repre import plot_decision_boundary

np.random.seed(666)


def get_data():
    
    X=np.random.normal(0,1,size=(200,2))
    
    y=np.array(X[:,0]**2+X[:,1]<1.5,dtype='int') ## y是 0,1 
    
    '''
    add noise to original data
    '''
    
    for _ in range(20):
        
        y[np.random.randint(200)]=1
    
    
    #plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    #plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    #plt.show()
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    return X_train,X_test,y_train,y_test


def sk_logestic_regression(X,y): ## 默认就是一个线性的回归，没有使用多项式
    
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    sk_log_lin_reg=LogisticRegression()
    
    sk_log_lin_reg.fit(X_train,y_train)
     
    score=sk_log_lin_reg.score(X_test,y_test)
    
    print("simple_logestic_socre=",score)
    
    plot_decision_boundary(sk_log_lin_reg, axis=[-4,4,-4,4])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    plt.show()
    
     
def PolynomialLogesticRegression(degree):
    
    return Pipeline([('poly',PolynomialFeatures(degree=degree)),\
        
        ('stand',StandardScaler()),\
        
        ('logestic_reg',LogisticRegression())
        ]) 
    


def PolynomialLogesticRegression02(degree,C,penalty='l2'): ##使用C 作为正则化项 CJ(theta)+L2 ,默认是L2正则化
    
    return Pipeline([('poly',PolynomialFeatures(degree=degree)),\
        
        ('stand',StandardScaler()),\
        
        ('logestic_reg',LogisticRegression(C=C,penalty=penalty))
        ]) 
    
def sk_poly_logestic_regresssion(X,y):

    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
    
    sk_poly_log_reg=PolynomialLogesticRegression(degree=20)
    
    sk_poly_log_reg.fit(X_train,y_train)
    
    score=sk_poly_log_reg.score(X_test,y_test)
    
    print("logestic_poly_score=",score)
    
    plot_decision_boundary(sk_poly_log_reg, axis=[-4,4,-4,4])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    plt.show()
    

def sk_poly_logestic_regular_regresssion(X,y):  
    
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
     
    sk_poly_log_regluar_reg=PolynomialLogesticRegression02(degree=20,C=0.1)
    
    sk_poly_log_regluar_reg.fit(X_train, y_train)   
    
    score=sk_poly_log_regluar_reg.score(X_test,y_test)
    
    print("logestic_poly_regular_score=",score)
    
    plot_decision_boundary(sk_poly_log_regluar_reg, axis=[-4,4,-4,4])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    plt.show()
    
    
def sk_poly_logestic_regular1_regresssion02(X,y):  
    
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.2,random_state=666)
     
    sk_poly_log_regluar_reg=PolynomialLogesticRegression02(degree=2,C=0.1,penalty='l1') ##　使用L1正则化
    
    sk_poly_log_regluar_reg.fit(X_train, y_train)   
    
    score=sk_poly_log_regluar_reg.score(X_test,y_test)
    
    print("logestic_poly_regular_score=",score)
    
    plot_decision_boundary(sk_poly_log_regluar_reg, axis=[-4,4,-4,4])
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.6)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.6)
    
    plt.show()
    
def main():
    
    X,y=get_data()
   
    #sk_logestic_regression(X, y)
    
    #sk_poly_logestic_regresssion(X,y)
    
    sk_poly_logestic_regular_regresssion(X,y)
    
    #sk_poly_logestic_regular1_regresssion02(X, y)

if __name__ == '__main__':
    
    main()