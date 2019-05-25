'''
Created on 2019年2月11日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split

from sklearn.datasets import load_digits

from sklearn.linear_model import LogisticRegression

from test03.metrics import TPR,FPR

from sklearn.metrics import roc_curve,roc_auc_score




def get_data():
    
    digits=load_digits()
    
    X=digits.data
    
    y=digits.target.copy()
    
    y[digits.target==9]=1
    
    y[digits.target!=9]=0
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=666)
    
    return X_train,X_test,y_train,y_test

def get_ROC_curve(X,y):
    
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    log_reg=LogisticRegression(C=1.0,penalty="l2")
    
    log_reg.fit(X_train,y_train)
    
    #y_predict=log_reg.predict(X_test)
    
    decision_scores=log_reg.decision_function(X_test)
    
    thresholds=np.arange(np.min(decision_scores),np.max(decision_scores),0.01)
    
    TPR_res=np.empty(shape=len(thresholds))
    
    FPR_res=np.empty(shape=len(thresholds))
    
    i=0
    
    for threshold in thresholds:
        
        y_predict_th=np.array(decision_scores>=threshold,dtype="int")
        
        TPR_res[i]=TPR(y_test, y_predict_th)
        
        FPR_res[i]=FPR(y_test, y_predict_th)
    
        i=i+1
        
        
    plt.plot(thresholds,TPR_res,alpha=0.5)
    
    plt.plot(thresholds,FPR_res,alpha=0.5)
    
    plt.show()
    
    plt.plot(FPR_res,TPR_res,alpha=0.5,color="g")
    
    plt.show()
    
    
def use_sk_roc_curve(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    log_reg=LogisticRegression(C=1.0,penalty="l2")
    
    log_reg.fit(X_train,y_train)
    
    #y_predict=log_reg.predict(X_test)
    
    decision_scores=log_reg.decision_function(X_test)
    
    fpr,tpr,thresholds=roc_curve(y_test, decision_scores )
    
    roc_under_score=roc_auc_score(y_test,decision_scores)
    
    print("roc_under_Score=",roc_under_score)
    
    plt.plot(fpr,tpr,alpha=0.5,color="m")
    
    plt.show()

def main():
    
    X,y=get_data()
    
    get_ROC_curve(X, y)
    
    #use_sk_roc_curve(X, y)

if __name__ == '__main__':
    main()