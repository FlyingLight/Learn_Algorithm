'''
Created on 2019年2月3日

@author: qiguangqin
'''

import numpy as np

from sklearn.datasets import load_digits

from sklearn.linear_model import LogisticRegression

from sklearn.model_selection import train_test_split

from sklearn.metrics import confusion_matrix

from sklearn.metrics import precision_score

from sklearn.metrics import recall_score

from sklearn.metrics import f1_score

from classification_evaluation.f1_score import f1_score_test


def get_data():
    
    digits=load_digits()
    
    X=digits.data
    
    y=digits.target.copy() ## digits.target与y，如果不是copy，
    
    y[digits.target==9]=1  ## 制造一个skewed data  "1" 是"0"data 数量 的1/9
    
    y[digits.target!=9]=0 ## 变成一个二分类问题
    
    return X,y

def TN(y_true,y_predict): ## 在测试数据中，返回TN(true negative 的数量)
    
    assert len(y_true)==len(y_predict)
    
    return np.sum((y_true==0) & (y_predict==0))


def FP(y_true,y_predict): ## 在测试数据中，返回FP(false postive 的数量) 
    
    assert len(y_true)==len(y_predict)
    
    return np.sum((y_true==0) & (y_predict==1))


def FN(y_true,y_predict): ## 在测试数据中，返回FN(false negative 的数量) 
    
    assert len(y_true)==len(y_predict)
    
    return np.sum((y_true==1) & (y_predict==0))

def TP(y_true,y_predict): ## 在测试数据中，返回FN(false negative 的数量) 
    
    assert len(y_true)==len(y_predict)
    
    return np.sum((y_true==1) & (y_predict==1))


def confusion_matrix01(y_true,y_predict):
    
    return np.array([[TN(y_true, y_predict),FP(y_true, y_predict)],[FN(y_true, y_predict),TP(y_true,y_predict)]])


def use_sk_confusion_matrix(y_true,y_predict):
    
    cm=confusion_matrix(y_true, y_predict)
    
    #print("confusion_matrix")
    
    return cm

def precision_score01(y_true,y_predict):
    
    tp=TP(y_true,y_predict)
    
    fp=FP(y_true,y_predict)

    try:
        
        return tp/(tp+fp)
    
    except:
        
            return 0.0
        
        
def recall_score01(y_true,y_predict):
    
    tp=TP(y_true,y_predict)
    
    fn=FN(y_true,y_predict)

    try:
        
        return tp/(tp+fn)
    
    except:
        
            return 0.0

def use_log_recall_eva():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,random_state=666)
    
    log_reg=LogisticRegression()
    
    log_reg.fit(X_train,y_train)
    
    y_predict=log_reg.predict(X_test)
    
    score=log_reg.score(X_test,y_test)
    
    print("predict_fscore=",score)
    
    #cm=confusion_matrix01(y_test, y_predict)
    
    cm=use_sk_confusion_matrix(y_test, y_predict)

    #precision_score1=precision_score01(y_test, y_predict)
    
    precision_score_res=precision_score(y_test, y_predict)
    
    #recall_score1=recall_score01(y_test, y_predict)
    
    recall_score_res=recall_score(y_test, y_predict)
    
    print("confusion_matrix=",cm,"precision_score=",precision_score_res,"recall_score=",recall_score_res)
    
    f1_score_res=f1_score_test(precision_score_res, recall_score_res)
    
    print("f1_score=",f1_score_res)
    
    f1_score_sk=f1_score(y_test,y_predict)
    
    print("f1_score_sk=",f1_score_sk)  ## recall=0.8 precision=0.90  f1_Score 就被拉低了
    
    
def main():
    
    use_log_recall_eva()
    


if __name__ == '__main__':
    main()