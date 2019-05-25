'''
Created on 2019年2月8日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import load_digits

from sklearn.model_selection import train_test_split

from sklearn.linear_model import LogisticRegression

from sklearn.metrics import f1_score

from sklearn.metrics import confusion_matrix

from sklearn.metrics import recall_score

from sklearn.metrics import precision_score

from sklearn.metrics import precision_recall_curve



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

def use_log_reg_new_decision():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_reg=LogisticRegression()
    
    log_reg.fit(X_train,y_train)
    
    y_predict=log_reg.predict(X_test) ## y_predict 的决策边界(score=X_b.dot(theta) >0 p>0.5决策为1; score<0,p<0.5,决策为0 )
    
    cm=confusion_matrix(y_test,y_predict)
    
    print("confusion_matrix=",cm)
    
    recall_score_res=recall_score(y_test,y_predict)
    
    print("recall_score=",recall_score_res)
    
    precision_score_res=precision_score(y_test,y_predict)
    
    print("precision_score=",precision_score_res)
    
    f1_score_res=f1_score(y_test, y_predict)
    
    print("f1_score=",f1_score_res)
    
    decision_scores=log_reg.decision_function(X_test)  ## 返回score=theta.dot(X_test)的值
    
    min_max=np.array([np.max(decision_scores),np.min(decision_scores)],dtype='float')
    
    print("min_max=",min_max)
    
    y_predict2=np.array(decision_scores>=-5,dtype="int")
    
    precision_score_res2=precision_score(y_test,y_predict2)
    
    recall_score_res2=recall_score(y_test,y_predict2)
    
    f1_score_res2=recall_score(y_test,y_predict2)
    
    cm2=confusion_matrix(y_test,y_predict2)
    
    print("confusion_matrix>-5=",cm2)
    
    print("pre_score>-5=",precision_score_res2,"recall_score>-5=",recall_score_res2,"f1_score>-5=",f1_score_res2)
    
 
def precision_recall_curve02():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_reg=LogisticRegression()
    
    log_reg.fit(X_train,y_train)
    
    y_predict=log_reg.predict(X_test) ## y_predict 的决策边界(score=X_b.dot(theta) >0 p>0.5决策为1; score<0,p<0.5,决策为0 )    
    
    decision_score=log_reg.decision_function(X_test)   ## 得到 X_test.dot(theta) 
    
    thresholds= np.arange(np.min(decision_score),np.max(decision_score),0.1)
    
    #print("threshold",thresholds)
    
    precis_score_ary=np.empty(shape=len(thresholds))
    
    recall_score_ary=np.empty(shape=len(thresholds))
    
    print(len(precis_score_ary),len(recall_score_ary))
    
    i=0
    
    for threshold in thresholds:
        
        y_predict3=np.array(decision_score>=threshold,dtype="int")
        
        precis_score_ary[i]=precision_score(y_test,y_predict3)
        
        recall_score_ary[i]=recall_score(y_test,y_predict3)
        
        i=i+1
        
    plt.plot(thresholds,precis_score_ary,alpha=0.5)
    
    plt.plot(thresholds,recall_score_ary,alpha=0.5)
    
    plt.show()
    
    plt.plot(precis_score_ary,recall_score_ary,alpha=0.5)
    
    plt.show()
    
    
def precision_sk_recall_curve():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_reg=LogisticRegression()
    
    log_reg.fit(X_train,y_train)
    
    y_predict=log_reg.predict(X_test) ## y_predict 的决策边界(score=X_b.dot(theta) >0 p>0.5决策为1; score<0,p<0.5,决策为0 )    
    
    decision_score=log_reg.decision_function(X_test)

    precisions,recalls,thresholds=precision_recall_curve(y_test, decision_score)
    
    '''

            不同的thresholds(X_b.dot(theta) > threshold )-->  precision and recalls 的值，
    
    thresholds 的step 根据 y_test 自动选取 ，precision 和 recalls 最后一个值 
    
    thresholds的len 数组比 precisions 和 recalls 少一个元素，（precision 和recalls ）最后一个元素分别是1,0
    '''
    
    plt.plot(thresholds,precisions[:-1],alpha=0.5)
    
    plt.plot(thresholds,recalls[:-1],alpha=0.5)   ##　并没有从最小开始绘制
    
    plt.show()
    
def main():
    
    use_log_reg_new_decision()
    
    precision_recall_curve02()
    
    precision_sk_recall_curve()

if __name__ == '__main__':
    main()