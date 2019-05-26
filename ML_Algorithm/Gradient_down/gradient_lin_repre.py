'''
Created on 2018年10月9日

@author: qiguangqin
'''

import numpy as np

from test03.train_test_split import train_test_split

from test03.metrics import r2_score

from sklearn.datasets import load_boston

from sklearn.preprocessing import StandardScaler

class LinearResgression02:
    
    " using gradient descent to estimate linear regression "
    
    def __init__(self):
        
        self.coef_=None
        
        self.interception_=None
        
        self._theta=None
        
        
    def fit_gd(self,X_train,y_train):
        
        assert X_train.shape[0]==y_train.shape[0],"X_train and y_train must be equal"
        
        def J(theta,X_b,y):
            
            try:
                res=np.sum((X_b.dot(theta)-y)**2)/len(X_b)
                
                return res

            except:
                    return float('inf')
             
        def dJ(theta,X_b,y):
            
            res=np.empty(shape=len(theta))
            
            res[0]=np.sum(X_b.dot(theta)-y)
            
            for i in range(1,len(theta)):
                
                res[i]=(X_b.dot(theta)-y).dot(X_b[:,i])
                
            return res*2.0/len(X_b)   
        
        def dJ1(theta,X_b,y):
            
            '''
            using vector to express gradient
            '''
            
            res=X_b.T.dot(X_b.dot(theta)-y)   
            
            return res*2.0/len(X_b)
        
        def  dJ_sgd(theta,X_b_i,y_i):
            
            res=X_b_i.T.dot(X_b_i.dot(theta)-y_i)
            
            return res*2.0
        
        def gradient_descent(initial_theta,eta,X_b,y,n_iter=1e4,epsilon=1e-8): 
            
            '''
            eta :learning rate, if too large result in NOT Convergent
            '''
        
            theta=initial_theta
            
            i_iter=0
            
            while (i_iter<n_iter):
                
                last_theta=theta
                
                gradient=dJ1(theta,X_b,y)
                
                theta=theta-eta*gradient
                
                if(abs(J(theta,X_b,y)-J(last_theta,X_b,y))<epsilon):
                    
                    break
        
                i_iter+=1
            
            return theta
        
        def gradient_descent_sgd(initial_theta,X_b,y,n_iters=100,t0=5,t1=50):
            
            assert n_iters>=1,"at least loop numbers"
                    
            def learning_rate(t):
                
                return t0/(t+t1)
            
            m=len(X_b)
            
            theta=initial_theta
            
            for cur_iter in range(n_iters):
                
                indexes=np.random.permutation(m)
            
                X_b_new=X_b[indexes]
            
                y_new=y[indexes]
            
                for i in range(m):  ## 要确保随机梯度下降法，把每一个样本都看了一遍
            
                    gradient=dJ_sgd(theta, X_b_new[i], y_new[i])
                
                    theta=theta-learning_rate(m*cur_iter+i)*gradient
                    
            return theta
            
        X_b=np.hstack([np.ones((len(X_train),1)),X_train])
        
        eta=0.01
        
        initial_theta=np.zeros(X_b.shape[1])  ##　initial_theta is a vector with n*1 
        
        theta=gradient_descent_sgd(initial_theta,X_b, y_train)
        
        self.coef_=theta[1:]
        
        self._theta=theta
        
        self.interception_=theta[0]
           
        return self
       
    
    def predict(self,X_predict):
        
        assert X_predict.shape[1]==len(self.coef_),"coefficient dimension must same with X_predict"
        
        assert self.coef_ is not None and self.interception_ is not None,"after fitting,coefficient and must not be None"
        
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
    
    stand=StandardScaler()
    
    stand.fit(X_train)
    
    X_train_stand=stand.transform(X_train)
    
    X_test_stand=stand.transform(X_test)
    
    return X_train_stand,X_test_stand,y_train,y_test
        
def main():
    
    X,y=get_data()
    
    X_train_stand,X_test_stand,y_train,y_test=get_train_test(X,y)
    
    lin_reg02=LinearResgression02()
    
    lin_reg02.fit_gd(X_train_stand, y_train)

    score=lin_reg02.score(X_test_stand, y_test)
    
    print("score=",score,"intercept=",lin_reg02.interception_,"coef=",lin_reg02.coef_)

if __name__ == '__main__':
    
    main()