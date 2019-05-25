# -*- coding: UTF-8 -*-
'''
Created on 2018��8��28��

@author: qiguangqin
'''

import numpy as np

from math import sqrt

'''
knn estimator
'''

def accuracy_score(y_true,y_predict):
    
    assert y_true.shape[0]==y_predict.shape[0], \
    " y_predict and y_test must be equal"
    
    score= sum(y_true==y_predict)/len(y_true)
    
    return score


'''
simple_lineare_regression estimator
'''

def get_mse(y_true,y_predict):
    
    assert y_true.shape[0]==y_predict.shape[0],"y_true and y_predict must be equal"
    
    mse=np.sum((y_predict-y_true)**2)/len(y_true)
    
    return mse
    
    
def get_rmse(y_true,y_predict):  ## rmse 因为是 square后，相加 再sqrt 所以有把 误差放大的趋势 略大于mae 
    
    assert y_true.shape[0]==y_predict.shape[0],"y_true and y_predict must be equal"
     
    rmse=sqrt(np.sum((y_predict-y_true)**2)/len(y_true))
     
    return rmse

def get_mae(y_true,y_predict):
    
    assert y_true.shape[0]==y_predict.shape[0],"y_true and y_predict must be equal"
      
    mae=np.sum(np.absolute(y_predict-y_true))/len(y_true)
      
    return mae

def r2_score(y_true,y_predict):  ##　得出R2
    
    mse=get_mse(y_true, y_predict)
   
    var=np.var(y_true)
   
    r2_score1=1-mse/var
   
    return r2_score1
    
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



def confusion_matrix(y_true,y_predict):
    
    return np.array([[TN(y_true, y_predict),FP(y_true, y_predict)],[FN(y_true, y_predict),TP(y_true,y_predict)]])

def precision_score(y_true,y_predict):
    
    tp=TP(y_true,y_predict)
    
    fp=FP(y_true,y_predict)

    try:
        
        return tp/(tp+fp)
    
    except:
        
            return 0.0
        
        
def recall_score(y_true,y_predict):
    
    tp=TP(y_true,y_predict)
    
    fn=FN(y_true,y_predict)

    try:
        
        return tp/(tp+fn)
    
    except:
        
            return 0.0  
        
        

def TPR(y_true,y_predict):
    
    tp=TP(y_true, y_predict)
    
    fn=FN(y_true, y_predict)
    
    try:
        
        return tp/(tp+fn)
    
    except:
        
        return 0.0
    
    
def FPR(y_true,y_predict):
    
    
    fp=FP(y_true, y_predict)
    
    tn=TN(y_true,y_predict)
    
    try:
        
        return fp/(fp+tn)
    
    except:
        
        return 0.0
        
    

if __name__ == '__main__':
    
    
    pass