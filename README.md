目标计划管理系统
============


## 前言
时常会觉得事情很多，让自己弄得很忙碌，有时候还会忘记或者延迟一些事情。等所有都忙碌结束以后却感觉自己收获不多，具体是什么原因呢？自己感觉主要有两方面的原因，第一：忙碌的事情可能是一些紧急但是不重要的事情。第二：我们没有去回顾自己忙碌的事情，也许收获很多，但是记忆却不明显。因此就针对自己的情况写了一个目标计划管理的小系统。


## 需求 && 实现
1、以年为单位制定自己的年度目标
2、将目标细分，分为每月目标，每周目标
3、制定明日的详细计划并对今日计划完成情况进行打分和总结
4、列出需要紧急处理的事情以及需要一直持之以恒坚持的事情
5、对自己的每日计划进行回顾


## Build Setup
```
# install dependencies
npm install

# server with hot reload at localhost:8080
npm run dev

# build production
npm run build
```


## 项目目录

```
xiaoD2018
|
├── xiaoD2018                          #java代码
├── src                                
|  ├── components                            
|     ├── content                       
|        ├── vue-adhere.vue            # 持之以恒模块
|        ├── vue-conclusion.vue        # 今日总结模块
|        └── vue-month.vue             # 月目标模块
|        └── vue-page.vue              # 页码模块
|        └── vue-plan.vue              # 计划模块
|        └── vue-return.vue            # 历史回顾模块
|        └── vue-search.vue            # 搜索框模块
|        ├── vue-urgency.vue           # 迫在眉睫模块
|        └── vue-week.vue              # 周目标模块
|        └── vue-year.vue              # 总目标模块
|     ├── vue-content			       # 展示内容
|     ├── vue-header.vue               # 头部模块
|     ├── vue-manage.vue               # 目标制定模块
|     ├── vue-nav.vue                  # 导航模块
|  ├── element                              
|     └── index.js                     # 按需引入element-ui的控件
|  └── router
|     └── index.js                     # 路由(懒加载)
|  └── store
|     └── index.js                     # Vuex
|  ├── app.vue
|  ├── index.js                        
├── .babelrc                           # 设置转码和规则
├── index.html                         
├── package.json                       # 依赖  
├── README.md                        
└── webpack.config.js                  # webpack配置文件
```

## 项目总结
在这次的项目开发中，后台采用java提供统一接口的模式继续开发，在操作数据库的时候通过hibernate。前端开发还是采用自己比较喜欢的Vue element-ui组合。在整个项目开发中进行请求统一采用的是post请求，这一点是很不好的，下一个项目一定会采用restful架构。另外对于前端页面的模块化开发做得还不够好，模块化程度不够高，主要原因是自己这个项目完成得太匆忙，只用了几天时间，前期的功能设计以及模块具体怎么设计都只是在脑海中有一个规划，没有通过画图的方式去进行分析。


## 项目展示
![image](https://github.com/deng1234/manage-plan/blob/master/_image/1.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/2.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/3.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/4.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/5.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/6.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/7.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/8.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/9.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/10.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/11.png)
![image](https://github.com/deng1234/manage-plan/blob/master/_image/12.png)