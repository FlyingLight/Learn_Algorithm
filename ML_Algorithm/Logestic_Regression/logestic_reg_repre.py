'''
Created on 2018年12月18日

@author: qiguangqin
'''

import numpy as np

from sklearn.datasets import load_iris 

from test03.metrics import accuracy_score

import matplotlib.pyplot as plt

from sklearn.model_selection import train_test_split

from matplotlib.colors import ListedColormap

from sklearn.neighbors import KNeighborsClassifier

from sklearn.preprocessing import StandardScaler

class Logestic_regression:
    
    def __init__(self):
        
        '''
        init_logestic_para
        '''
        
        self.coef_=None    ## 逻辑回归的初始化参数
        
        self.interception_=None
        
        self._theta=None
        
        
    def _sigmoid(self,t):
            
        return 1./(1.+np.exp(-t))
        
    
    def fit(self,X_train,y_train):
        
        assert X_train.shape[0]==y_train.shape[0] ## y 必须是数组 np 或者是 matrix
        
        def J(theta,X_b,y):
            
            m=len(X_b)
            
            p_hat=self._sigmoid(X_b.dot(theta))
            
            try:
            
                #res=np.sum(y*np.log(p_hat)+(1-y)*np.log(1-p_hat))
                
                res=y.dot(np.log(p_hat))+(1-y).dot(np.log(1-p_hat))
                
                '''
                SCORE = theta.dot(X_b) >0  p>0.5 --->1
                
                SCORE <0 P<0.5 --->0 
                '''
                return res*(-1/m)
            
            except:
                
                return float('inf')
        
        
        def dJ_ve(theta,X_b,y):
            
            m=len(X_b)
            
            p_hat=self._sigmoid(X_b.dot(theta))
            
            res=X_b.T.dot(p_hat-y)

            return res*(1/m)
        
         
        def dJ(theta,X_b,y):
            
            m=len(X_b)
            
            n=len(theta)
            
            res=np.empty(len(theta),dtype='float')
            
            p_hat=self._sigmoid(X_b.dot(theta))
            
            res[0]=np.sum(p_hat-y)
            
            for i in range(1,n+1):
                
                res[i]=(p_hat-y).dot(X_b[:,i])
                 
            return res*(1/m)  
        
        
        def dJ2(X_b,theta):
            
            m=X_b.shape[0]
            
            mk=(self._sigmoid(X_b.dot(theta)))/(1-self._sigmoid(X_b.dot(theta)))
            
            res=(X_b.T.dot(np.diag(mk))).dot(X_b)
                            
            return res/m

        def batch_gradient_descent(initial_theta,eta,X_b,y,n_iter=1e4,epsilon=1e-8):    
            
            theta=initial_theta
            
            i_iter=0.0
            
            while i_iter<n_iter:
                
                last_theta=theta
                
                gradient=dJ_ve(theta, X_b, y)
                
                theta=theta-gradient*eta
                
                if(np.abs(J(last_theta, X_b, y)-J(theta, X_b, y))<epsilon):
                    
                    break
                
                i_iter+=1
                  
            return theta 
        
        
        def get_min(X_b,theta,y,sigma,delta,d,g):
            
            m=0
            
            while True:
                
                theta_new=theta+pow(delta,m)*d
                
                left=J(theta_new, X_b, y)
                
                right=J(theta, X_b, y)+sigma*pow(delta, m)*g.T.dot(d)
                
                if left<=right:
                    
                    break
                
                else:
                
                    m+=1
                    
            return m
    
        def all_newton(X_b,y,initial_theta,sigma,delta,n_iters=1e4):
            
             
            theta=initial_theta
            
            i_iter=0
            
            while i_iter<n_iters:
                
                g=dJ_ve(theta, X_b, y)
                
                G=dJ2(X_b, theta)
                
                d=-(np.linalg.inv(G)).dot(g)
                
                m=get_min(X_b,theta,y,sigma,delta,d,g)
                
                theta=theta+pow(delta,m)*d
                
                i_iter+=1
                
            return theta 
                
                

        X_b=np.hstack([np.ones((len(X_train),1)),X_train])
        
        eta=0.01
        
        initial_theta=np.zeros(X_b.shape[1])  ## X_b.shape[1] add 1 column full of 1
        
        sigma=0.5
        
        delta=0.1
        
        self._theta=all_newton(X_b, y_train, initial_theta, sigma, delta)
        
        #self._theta=batch_gradient_descent(initial_theta, eta, X_b, y_train)
        
        self.coef_=self._theta[1:,]
        
        self.interception_=self._theta[0]
        
        return self
    
    
    def predict_proba(self,X_predict):
        
        '''
        Return X_predict  proba_vector
        '''
        
        assert X_predict.shape[1]==self.coef_.shape[0],"X_predict and coef_ must have same feautres"
        
        assert self.coef_ is not None and self.interception_ is not None,"after fitting,coefficient and must not be None"
        
        Xb_test=np.hstack([np.ones((len(X_predict),1)),X_predict])
        
        y_predict_pro=self._sigmoid(Xb_test.dot(self._theta))
        
        return y_predict_pro
    
    
    def predict(self,X_predict):
        
        '''
        According to proba,Return predict number
        
        '''
        
        y_predict=self.predict_proba(X_predict)
        
        return np.array(y_predict>=0.5,dtype='float')
    
    def score(self,X_test,y_test):
    
        y_predict=self.predict(X_test)
        
        score=accuracy_score(y_test, y_predict)  ### 由于是分类的准确度，所以不是调R2SCORE
        
        return score
    

def simple_logestic_boundary(algo,x1):
    
    '''
    Draw the judge edge => X_b.dot(theta)=0 ==> p_hat=0.5  ==>y=1
    
    '''
    
    x2=-(algo.coef_[0]*x1+algo.interception_)/(algo.coef_[1])
    
    plt.plot(x1,x2,alpha=0.5,color='g')
    
    #plt.show()


def use_stand_scaler(Seq):
    
    stand=StandardScaler()
    
    stand.fit(Seq)
    
    Seq_stand=stand.transform(Seq)
    
    return Seq_stand

def original_point(X,y):
    
    plt.scatter(X[y==0,0],X[y==0,1],alpha=0.8)
    
    plt.scatter(X[y==1,0],X[y==1,1],alpha=0.8,color='m')
    
    plt.show()
    

def plot_decision_boundary(model,axis):  ##　绘制决策边界
    
    x0,x1=np.meshgrid(np.linspace(axis[0],axis[1],int((axis[1]-axis[0])*100)).reshape(-1,1),\
                      
                      np.linspace(axis[2],axis[3],int((axis[3]-axis[2])*100)).reshape(-1,1),\
                    ) 
    
    X_new=np.c_[x0.ravel(),x1.ravel()] ## 按照行进行合并矩阵，连个矩阵左右相加
    
    y_predict=model.predict(X_new) 
    
    zz=y_predict.reshape(x0.shape)
    
    custom_cmap = ListedColormap(['#D3D3D3','#FFF59D','#B0C4DE'])
    
    plt.contourf(x0, x1, zz, cmap=custom_cmap)  
    
    

def draw_knn_clf_boundary_double(X_new,y_new):
    
    
    knn_clf=KNeighborsClassifier(n_neighbors=10)
    
    knn_clf.fit(X_new,y_new)
    
    plot_decision_boundary(knn_clf, axis=[4,8,1.5,5])
    
    #original_point(X_new, y_new)
    
    plt.scatter(X_new[y_new==0,0],X_new[y_new==0,1],alpha=0.8)
    
    plt.scatter(X_new[y_new==1,0],X_new[y_new==1,1],alpha=0.8,color='m')
    
    plt.show()
    
    
def draw_knn_clf_boundary_triple():
    
    knn_clf=KNeighborsClassifier(n_neighbors=50)
    
    iris=load_iris()
    
    knn_clf.fit(iris.data[:,:2],iris.target)
    
    plot_decision_boundary(knn_clf, axis=[4,8,1.5,5])
    
    plt.scatter(iris.data[iris.target==0,0],iris.data[iris.target==0,1],alpha=0.8)
    
    plt.scatter(iris.data[iris.target==1,0],iris.data[iris.target==1,1],alpha=0.8,color='m')
    
    plt.scatter(iris.data[iris.target==2,0],iris.data[iris.target==2,1],alpha=0.8)
    
    plt.show()
    
    
def get_data():
    
    iris=load_iris()
    
    X=iris.data
    
    y=iris.target
    
    return X,y   

def main():
    
    X,y=get_data()
    
    #X_new=np.vstack([X[y==0],X[y==1]])[:,:2]
    
    X_new=X[y<2,:2]
    
    #y_new=np.hstack([y[y==0],y[y==1]])
    
    y_new=y[y<2]
    
    #print(X_new.shape,y_new.shape)
    
    X_train,X_test,y_train,y_test=train_test_split(X_new,y_new,test_size=0.2,random_state=666)
    
    X_train_std= use_stand_scaler(X_train)
    
    X_test_std= use_stand_scaler(X_test)
    
    log_reg=Logestic_regression()
    
    log_reg.fit(X_train, y_train)
    
    y_proba=log_reg.predict_proba(X_test)
    
    y_pred=log_reg.predict(X_test)
    
    score=log_reg.score(X_test, y_test)
    
    print("y_pro",y_proba,y_pred,score)
    
    '''
    draw decision boundary of logestic regression
    '''
    
    simple_logestic_boundary(log_reg,np.linspace(4,8,1000))
    
    plot_decision_boundary(log_reg, axis=[4,8,1.5,5.5])
    
    original_point(X_new, y_new)
    
    '''
    knn two elements classifier
    '''
    
    draw_knn_clf_boundary_double(X_new, y_new)
    
    '''
    knn three elements classfier
    '''
    
    draw_knn_clf_boundary_triple()
    

if __name__ == '__main__':
    
    main()