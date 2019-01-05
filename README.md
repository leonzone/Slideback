
## 效果

![](https://github.com/leonzone/Slideback/blob/dd24bf8bb8949ec21cc0a35e3d847f9b0ba3cb0a/art/art.png)


## 添加依赖

``` groovy
 implementation 'com.reiser:slideback:0.0.1'
```

## 使用方法


- 注册

``` java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlideBack.register(this, new SlideBackCallBack() {
            @Override
            public void onSlideBack() {
                onBackPressed();
            }
        });

    }
```

> 注意注册后一定要取消注册，防止内存泄漏

- 取消注册

``` java
    @Override
    protected void onDestroy() {
        SlideBack.unRegister(this);
        super.onDestroy();
    }
```

