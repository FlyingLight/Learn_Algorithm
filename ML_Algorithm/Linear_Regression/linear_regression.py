'''
Created on 2018年9月20日

@author: qiguangqin
'''

import numpy as np

from sklearn.datasets import load_boston
from test03.metrics import r2_score
from test03.train_test_split import train_test_split


class LinearRegression:
    
    def __init__(self):
        
        " 初始化Linear Regression 模型"
        
        self.coef_=None ##系数初始化为None
        
        self.interception_=None  ## 初始化截距为None
        
        self._theta=None
    
    def fit(self,X_train,y_train):
        
        assert X_train.shape[0]==y_train.shape[0],"X_train must be equal with y_train  ,feature shape[1],样本数为shape[0]"
        
        Xb=np.hstack([np.ones((len(X_train),1)),X_train])
        
        '''
        ones(len(X_train),1)
        '''
        
        self._theta=(np.linalg.inv(Xb.T.dot(Xb))).dot(Xb.T.dot(y_train))
        
        self.interception_,self.coef_=np.hsplit(self._theta,[1])  ##返回都是两个矩阵,只有np 没有reshape 点乘 才是一个数字，其他要用 [] 引用
        
        return self
    
    def predict(self,X_predict):
        
        assert X_predict.shape[1]==self.coef_.shape[0],"coefficient dimension must same with X_predict"
        
        assert self.coef_ is not None and self.interception_ is not None,\
        " coefficient 不能是空，interception 不能是空"
        
        y_predict=[self._predict(x_predict) for x_predict in X_predict]
        
        return y_predict
    
    def _predict(self,x_predict):
        
        y_single_predict= (x_predict.T).dot(self.coef_)+self.interception_[0]
       
        return y_single_predict
    
    def score(self,X_test,y_test):
        
        y_predict=self.predict(X_test)
        
        y_predict_np=np.array(y_predict).reshape(-1,1)
        
        y_test_np=np.array(y_test).reshape(-1,1)
        
        score=r2_score(y_test_np, y_predict_np)
        
        return score
            
class LinearRegression02:
    
    def __init__(self):
        
        " 初始化Linear Regression 模型"
        
        self.coef_=None ##系数初始化为None
        
        self.interception_=None  ## 初始化截距为None
        
        self._theta=None
    
    
    def fit_normal(self,X_train,y_train):
        
        assert X_train.shape[0]==len(y_train),"X_train and y_train must be same"
        
        Xb=np.hstack([np.ones(shape=(len(y_train),1)),X_train])
    
        self._theta=np.linalg.inv(Xb.T.dot(Xb)).dot(Xb.T.dot(y_train))
        
        self.interception_=self._theta[0]
    
        self.coef_=self._theta[1:]
        
        return self
    
    
    def predict(self,X_predict):
        
        assert X_predict.shape[1]==len(self.coef_),"coefficient dimension must same with X_predict"
        
        assert self.coef_ is not None and self.interception_ is not None,\
        " coefficient 不能是空，interception 不能是空"
        
        Xb_test=np.hstack([np.ones(shape=(len(X_predict),1)),X_predict])
        
        y_predict=Xb_test.dot(self._theta)
        
        return y_predict
    
    def score(self,X_test,y_test):
        
        y_predict=self.predict(X_test)
        
        score=r2_score(y_test, y_predict)
        
        return score
        
    
def get_data():
    
    boston=load_boston()  ##一共有506个样本，每一个样本有13个特征，简单线性回单，RM room的多少来预测

    x=boston.data

    y=boston.target

    X=x[y<50.0]  ##使用fancy indexing ，把最大值给去掉,形成真正的数据集

    y=y[y<50.0]
    
    return X,y


def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y)
    
    return X_train,X_test,y_train,y_test
    

def main():
     
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=get_train_test(X,y)
    
    lr=LinearRegression()
    
    lr.fit(X_train,y_train)
    
    print("score=",lr.score(X_test, y_test))
    
    lr1=LinearRegression02()
    
    lr1.fit_normal(X_train, y_train)
    
    print("score=",lr1.score(X_test, y_test))
        
if __name__ == '__main__':
    
    main()
