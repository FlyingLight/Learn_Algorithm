'''
Created on 2019年6月6日

@author: qiguangqin
'''

import numpy as np

import matplotlib.pyplot as plt

from collections import Counter

from sklearn.datasets import load_iris

from sklearn.model_selection import train_test_split

from sklearn.metrics import accuracy_score

from matplotlib.colors import ListedColormap

from sklearn.preprocessing import StandardScaler



def get_data():
    
    iris=load_iris()
    
    X=iris.data
    
    y=iris.target
    
    return X,y

def get_train_test(X,y):
    
    X_train,X_test,y_train,y_test=train_test_split(X,y,test_size=0.3,random_state=666)
    
    return X_train,X_test,y_train,y_test 

def use_stand_scaler(Seq):
    
    stand=StandardScaler()
    
    stand.fit(Seq)
    
    Seq_stand=stand.transform(Seq)
    
    return Seq_stand

class soft_max_regression():
    
    def __init__(self):
        
        self.coef_=None
        
        self.interception_=None
        
        self.theta=None
    
    def _sigmoid(self,t):
            
        return 1./(1.+np.exp(-t))
    
    def fit(self,X_train,y_train):
        
        assert X_train.shape[0]==y_train.shape[0],"X_train and y_train must be equal"
        
        k=len(Counter(y_train))
        
        
        def J(theta,X_b,y):
            
            m,n=X_b.shape
            
            '''
            theta ~(n+1,k)
            
            X_b_i ~(1,n+1)
            
            ~(1,k)  X_b_i.dot(theta)
            '''
            sum1=0.0
            
            for i in range(m):
                
                k=y[i]
                
                theta_k=theta[:,k].reshape(-1,1) ## (n+1,1)
                
                X_b_i=X_b[i].reshape(1,-1) ##(1,n+1)
                
                res=(X_b_i.dot(theta_k))[0]+np.log(np.sum(np.exp(X_b_i.dot(theta[:,:k].reshape(n,-1))),axis=1))  ##(n+1,)
                
                sum1+=res

            return sum1
        
        def dJ_sgd(theta, X_b_i, y_i):
            
            ## theta ~(n+1,k)
            
            X_b_i=X_b_i.reshape(1,-1) ## (1,n+1)
            
            ex=np.exp((X_b_i.dot(theta)).T) ##(1,n+1) *(n+1,k) .T(k,1)
            
            y_k_i=np.where(np.arange(k).reshape(k,1)==y_i,1,0) ##~(k,1)
 
            num=np.sum(np.exp(X_b_i.dot(theta)),axis=1)
            
            res=((ex/num)-y_k_i).dot(X_b_i) ##(k,n+1)
            
            return res.T  ##(k,n+1).T  (n+1,k)
        
        def dJ(theta,X_b,y):
            
            m,n=X_b.shape  ##(m,n+1)
            
            sum1=np.zeros(shape=(n,k),dtype="float") ##(n+1,k)
             
            for i in range(m):
                
                X_b_i=X_b[i].reshape(1,-1)
                
                ex=np.exp((X_b_i.dot(theta)).T) ##(1,n+1) *(n+1,k) .T(k,1)
            
                y_k_i=np.where(np.arange(k).reshape(k,1)==y[i],1,0) ##~(k,1)
 
                num=np.sum(np.exp(X_b_i.dot(theta)),axis=1)
            
                res=((ex/num)-y_k_i).dot(X_b_i) ##(k,n+1)
                
                sum1+=res.T
                
            return sum1/(m)
            
        def batch_gradient_descent(initial_theta,X_b,y,eta=0.01,n_iter=1e4,epsilon=1e-8):    
            
            theta=initial_theta
            
            i_iter=0.0
            
            while i_iter<n_iter:
                
                last_theta=theta
                
                gradient=dJ(theta, X_b, y)
                
                theta=theta-gradient*eta
                
                if(np.abs(J(last_theta, X_b, y)-J(theta, X_b, y))<epsilon):
                    
                    break
                
                i_iter+=1
                  
            return theta 
        
            
        def gradient_descent_sgd(initial_theta,X_b,y,n_iters=1000,t0=5,t1=50):
            
            assert n_iters>=1,"at least have 1 loop"
            
            def learning_rate(t):
                
                return t0/(t1+t)
            
            m=X_b.shape[0]
            
            theta=initial_theta
            
            for i_iter in range(n_iters):
                
                index=np.random.permutation(m)
                
                X_b_new=X_b[index]
                
                y_new=y[index]
                
                for i in range(m):
                    
                    gradient=dJ_sgd(theta, X_b_new[i], y_new[i])
                
                    theta=theta-learning_rate(m*i_iter+i)*gradient
                    
                    #theta=theta-eta*gradient
            
            return theta
        
        X_b=np.hstack([np.ones((len(X_train),1)),X_train])
        
        initial_theta=np.zeros((X_b.shape[1],k)) ##(n+1,k)
        
        theta=gradient_descent_sgd(initial_theta,X_b, y_train)
        
        #theta=batch_gradient_descent(initial_theta, X_b, y_train)
        
        self.theta=theta
        
        self.interception_=theta[0,] ##(k,)
        
        self.coef_=theta[1:,] ##(n,k)
        
        return self
    
    def predict(self,X_predict):
        
        assert X_predict.shape[1]==self.coef_.shape[0],"X_predict and coef_ must have same feautres"
        
        assert self.coef_ is not None and self.interception_ is not None,"after fitting,coefficient and must not be None"
        
        Xb_test=np.hstack([np.ones((len(X_predict),1)),X_predict]) ##(m',n+1)
        
        y_predict=np.argmax(Xb_test.dot(self.theta),axis=1)  ##(m',k)
        
        return y_predict
    
    def score(self,X_test,y_test):
    
        y_predict=self.predict(X_test)
        
        score=accuracy_score(y_test, y_predict)  ### 由于是分类的准确度，所以不是调R2SCORE
        
        return score
    
def plot_decision_boundary(model,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    print("X_new.shape",X_new.shape)
    
    y_predict=model.predict(X_new) 
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap)  
    
def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test =get_train_test(X, y)
    
    X_train_std= use_stand_scaler(X_train)
    
    X_test_std= use_stand_scaler(X_test)
    
    sft_reg=soft_max_regression()
        
    sft_reg.fit(X_train_std,y_train)
    
    #print(sft_reg.coef_)
    
    y_predict=sft_reg.predict(X_test_std)
    
    score=sft_reg.score(X_test_std, y_test)
    
    print("Score=",score,"y_predict[:10]",y_predict[:10])
    
   
    
    plt.show()
    

if __name__ == '__main__':
    main()