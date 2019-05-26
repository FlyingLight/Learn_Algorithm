'''
Created on 2018年10月16日

@author: qiguangqin
'''


from sklearn.linear_model import SGDRegressor

from gradient_down_test.gradient_lin_repre import get_data

from test03.train_test_split import train_test_split

from sklearn.preprocessing import StandardScaler

'''
SGDRegrssor 只能解决线性回归模型

'''

def main():
    
    X,y=get_data()
    
    X_train,X_test,y_train,y_test=train_test_split(X, y, test_ratio=0.2, seed=666)
    
    stand=StandardScaler()
    
    stand.fit(X_train)
    
    X_train_std=stand.transform(X_train)
    
    X_test_std=stand.transform(X_test)
    
    sgd_reg=SGDRegressor(max_iter=50)  ## 定义遍历的次数！！对于整个样本浏览几次
    
    sgd_reg.fit(X_train_std,y_train)
    
    score=sgd_reg.score(X_test_std,y_test)
    
    print(score)

if __name__ == '__main__':
    
    main()