
## 效果

<img src="https://github.com/leonzone/Slideback/blob/master/art/art.png" width=270 height=480/>

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

