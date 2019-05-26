'''
Created on 2018年10月17日

@author: qiguangqin
'''

import numpy as np


from test03.metrics import r2_score

def J(theta,X_b,y):

    try:

        return np.sum((y-X_b.dot(theta))**2)/len(X_b)
    
    except:
        
        return float('inf')
    
    
def dJ(theta,X_b,y):
    
    
    res=X_b.T.dot(X_b.dot(theta)-y)
    
    return res*2.0/len(X_b)


def dJ_math(theta,X_b,y,epsilon=1e-8):
    
    '''
    using math definition to calculate 
    '''
    
    res=np.empty(shape=(len(theta)))
    
    
    for i in range(len(theta)):
        
        '''
            using math 
        '''
        theta_copy1=theta.copy()
    
        theta_copy2=theta.copy()
        
        theta_copy1[i]+=epsilon
        
        theta_copy2[i]-=epsilon
        
        
        res[i]=(J(theta_copy1,X_b,y)-J(theta_copy2, X_b, y))/(2.*epsilon)
              
    return res
        
def dJ_lgd(theta,X_b,y,k):
    
    
    res=X_b[:k].T.dot(X_b[:k].dot(theta[:k])-y[:k])
    
    return res*2.0/(k)
    
    
def gradient_descent(dJ,initial_theta,eta,X_b,y,n_iter=1e4,epsilon=1e-8): 
            
        '''
         eta :learning rate, if too large results in NOT Convergent
        '''
        
        theta=initial_theta
            
        i_iter=0
            
        while (i_iter<n_iter):
                
            last_theta=theta
                
                
            gradient=dJ(theta, X_b, y)
                
            theta=theta-eta*gradient
                
            if(abs(J(theta,X_b,y)-J(last_theta,X_b,y))<epsilon):
                    
                break
        
            i_iter+=1
            
        return theta

def little_batch_gradient_descent(dJ,initial_theta,X_b_k,y_b_k,t0=5,t1=50,n_iter=100):
    
        
        theta=initial_theta
        
        i_iter=0.0
        
        
        k=len(X_b_k)
        
        def learning_rate(t):
            
            res=t0/(t1+t)
            
            return res
        
        for i_iter in range(n_iter):
            
            indexes=np.random.permutation(k)
            
            X_b_k_new= X_b_k[indexes]
            
            y_b_k_new=y_b_k[indexes]
            
            for i in range(k):
                
                gradient=dJ(theta, X_b_k_new, y_b_k_new, k)
                
                theta=theta-learning_rate(k*i_iter+i)*gradient
            
            
        return theta
    
def main():
    
    np.random.seed(666)
    
    true_theta=np.arange(1,12,dtype=float)
    
    X=np.random.random(size=(1000,10))
    
    X_b=np.hstack([np.ones((len(X),1)),X])
    
    initial_theta=np.zeros(X_b.shape[1])
    
    eta=0.01
    
    y=X_b.dot(true_theta)+np.random.normal(0,1,size=(len(X_b)))
    
    theta=gradient_descent(dJ_math, initial_theta, eta, X_b, y)
    
    k=200
    
    theta1=little_batch_gradient_descent(dJ_lgd,initial_theta,X_b[:k],y[:k])
    
    
    print("theta",theta)
    
    print("theta1",theta1)
    
    y_predict=X_b.dot(theta)
    
    score=r2_score(y,y_predict)
    
    
    y_predict1=X_b.dot(theta1)
    
    score1=r2_score(y,y_predict1)
    
    print("score=",score,"score1=",score1)
    

if __name__ == '__main__':
    main()