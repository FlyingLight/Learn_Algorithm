#coding=utf-8
'''
Created on 2018��9��7��

@author: qiguangqin
'''
import numpy as np

from sklearn.datasets import load_iris

class standScaler:
    
    def __init__(self):
        
        self.mean_=None
        
        self.scale_=None
    
    def fit(self,X):
        
        assert X.ndim==2, \
        " Dimension of X must be 2"
        
        self.mean_= np.array([ np.mean(X[:,col]) for col in range(X.shape[1])])
        
        self.scale_= np.array([ np.std(X[:,col]) for col in range(X.shape[1])])
        
        return self
        
    def transform(self,X):
        
        assert X.ndim==2, " Dimension of X must be 2"
        
        assert self.mean_.shape[0]==X.shape[1], " mean_ and X must be equal"
        
        assert self.mean_ is not None and self.scale_ is not None
        
        '''
                新建一个矩阵，保存归一化的返回值
        '''
        
        res=np.empty(shape=X.shape,dtype=float)
        
        for col in range(len(self.mean_)):
        
            res[:,col]=(X[:,col]-self.mean_[col])/self.scale_[col]
               
        return res
       
        
class Max_min_scale:
        
    def __init___(self):
               
        self.max_=None
            
        self.min_=None
            
            
    def fit(self,X):
            
        assert X.ndim==2, " Dimension of X must be 2"
            
        self.max_= np.array([ np.max(X[:,col]) for col in range(X.shape[1]) ])
            
        self.min_= np.array([ np.min(X[:,col]) for col in range(X.shape[1]) ])
            
        return self
            
    def transform(self,X):  
             
        assert X.ndim==2, " Dimension of X must be 2" 
            
        assert self.max_ is not None and self.min_ is not None
            
        assert X.shape[1]==len(self.min_), "the feature numbers of X must be equal to min_"
            
        " 生产一个空的矩阵，用于标记"
            
        res=np.empty(shape=X.shape,dtype=float)
                
        for col in range(len(self.max_)):
                    
            res[:,col]=(X[:,col]-self.min_[col])/(self.max_[col]-self.min_[col])
                       
        return res
    
    
def get_data():
    
    
    iris=load_iris()
    
    X=iris.data
    
    y=iris.target
    
    return X,y
    
def main():
    
    X,y=get_data()
    
    stand2=standScaler()
      
    stand2.fit(X)
    
    X_stand=stand2.transform(X)
    
    print(X_stand.shape)
    
if __name__ == '__main__':
    main()