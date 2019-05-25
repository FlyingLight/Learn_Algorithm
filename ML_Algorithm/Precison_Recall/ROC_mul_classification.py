'''
Created on 2019年2月15日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from sklearn.datasets import load_digits

from sklearn.model_selection import train_test_split

from sklearn.linear_model import LogisticRegression

from sklearn.metrics import precision_score,recall_score,f1_score,confusion_matrix


def get_data():
    
    digits=load_digits()
    
    X=digits.data
    
    y=digits.target.copy() ## 一共有10个数字，就是一个10分类的问题
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.8,random_state=666)
    
    return X_train,X_test,y_train,y_test

def use_multi_classi_confusion_matrix(X,y):
    
    X_train,X_test,y_train,y_test=get_train_test(X, y)
    
    log_reg=LogisticRegression(C=1.0,penalty="l2")
    
    log_reg.fit(X_train,y_train)
    
    score= log_reg.score(X_test,y_test)
    
    print("score=",score)
    
    y_predict=log_reg.predict(X_test)
    
    ##precision_score 多分类要加上 average="micro" ,默认 average="binary"
    
    pre_score=precision_score(y_test, y_predict,average="micro")
    
    print("pre_score=",pre_score) #算出精准率
    
    rec_score=recall_score(y_test, y_predict,average="micro")
    
    print("rec_score=",rec_score)
    
    f1_score_res=f1_score(y_test,y_predict,average="micro")
    
    print("f1_score",f1_score_res)
    
    cm=confusion_matrix(y_test, y_predict)
    
    print("confusion_matrix=",cm)
    
    #plt.matshow(cm,cmap=plt.cm.gray)
    
    row_sums=np.sum(cm,axis=1)
    
    err_mat=cm/row_sums
    
    np.fill_diagonal(err_mat,0)
    
    print("err_mat",err_mat)
    
    plt.matshow(err_mat,cmap=plt.cm.gray)
    
    plt.show()
    
    
    
def main():
    
    X,y=get_data()
    
    use_multi_classi_confusion_matrix(X, y)

if __name__ == '__main__':
    main()