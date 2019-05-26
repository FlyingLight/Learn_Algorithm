'''
Created on 2018年11月16日

@author: qiguangqin
'''
import numpy as np

import matplotlib.pyplot as plt

from sklearn.preprocessing import PolynomialFeatures

from sklearn.linear_model import LinearRegression

from sklearn.pipeline import Pipeline

from sklearn.preprocessing import StandardScaler


def get_data():
    
    np.random.seed(666)
    
    x=np.random.uniform(-3,3,size=100)
    
    X=x.reshape(-1,1)
    
    y=0.5*x**2+x+2+np.random.normal(0,1,size=100)
    
    return X,y

def get_data2():

    X=np.arange(1,11).reshape(-1,2)

def poly_regression_repre():
    
    '''
        在数据预处理时候，使用 poly_进行，类似于StandScaler
    '''
    
    X,y=get_data()
    
    poly=PolynomialFeatures(degree=2)
    
    '''
    表示为原始数据集，添加最高几次幂,X2 增加X 的二次项，作为new_feauture,使用linear_regression
    '''
    poly.fit(X)
    
    X2=poly.transform(X)
    
    lin_reg=LinearRegression()
    
    lin_reg.fit(X2,y)
    
    print(X2[:2,:3]) ## 在左边增加了一列，1 X^0
    
    y_predict=lin_reg.predict(X2)
    
    print("coef=",lin_reg.coef_,"interception=",lin_reg.intercept_)
    
    plt.scatter(X[:,0],y,alpha=0.5)
    
    
    plt.plot(np.sort(X[:,0]),y_predict[np.argsort(X[:,0])],color='m') 
    
    plt.show()
    
    
def using_pipe_line():
    
    '''
    
    使用pipe_line,将polynomial feature，standscaler，lineargression ，做出一个管道,因为 sklearn 并没有给出一个 完整 poly 的类
    
    '''
    
    X,y=get_data()
    
    poly_reg=Pipeline([("poly",PolynomialFeatures(degree=2)),("stand_scaler",StandardScaler()),("lin_reg",LinearRegression())])
    
    poly_reg.fit(X,y) ## 需要将X，y 全部传入
    
    y_predict=poly_reg.predict(X)
    
    plt.scatter(X[:,0],y,alpha=0.5)
    
    plt.plot(np.sort(X[:,0]),y_predict[np.argsort(X[:,0])],alpha=0.7,color='m')

def main():
    
    poly_regression_repre()
    
    #using_pipe_line()

if __name__ == '__main__':
    
    main()