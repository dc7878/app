一点简单的介绍
#### 1.common 是一些公共的库，主要简单封装了MVP以及一些自定义的View

#### 2.custom view 是一些自定义View、ViewGroup相关的代码

#### 3.demo 开始是模仿斗鱼写的一个App的，后续它API更新速度太快，暂时暂停了，目前实现的功能有
1.API抓取使用的是charles，变动比较大，UI的变动相应的也非常大
2.实现的几个页面都是用的RecyclerView，主要处理了分割线、刷新、加载更多，功能不是很完善
3.播放器是使用的阿里云的点播播放器，目前能播放一些，但是直播过程的播放还是存在问题，
播放使用的是直播数据中的flv的数据，这样每次只能播放十几秒
4.还有一些其他的测试性代码在UserFragment里面
后续可能会继续完善

#### 4. memory training 主要是写给自己的一个记忆训练的App，基本上是进展为0，后续可能会完善