[![](https://jitpack.io/v/BaselHorany/DualButton.svg)](https://jitpack.io/#BaselHorany/DualButton)

# DualButton
a dual Button with some animations

<p align="center">
  <img src="https://github.com/BaselHorany/DualButton/blob/master/showcase.gif?raw=true" />
</p>


## Setup
add it as a dependency to your app `build.gradle`
```java
dependencies {
  compile 'com.github.BaselHorany:DualButton:1.0.1'
}
```

## Usage
1-in layout
```xml
        <com.basel.DualButton.DualButton
            app:textFirst="START"
            app:textSecond="CANCEL"
            app:textColorFirst="#ffffff"
            app:textColorSecond="#03A9F4"
            app:backgroundColorFirst="#03A9F4"
            app:backgroundColorSecond="#ffffff"
            android:id="@+id/dualBtn"
            android:layout_width="200dp"
            android:layout_height="45dp"
            android:layout_marginTop="25dp"/>
            <!--All options at the end -->
```
2- in code
```java
        DualButton dualBtn = findViewById(R.id.dualBtn);
        dualBtn.setDualClickListener(new DualButton.OnDualClickListener() {
            public void onClickFirst(Button btn) {
              //first btn has been clicked
            }
            public void onClickSecond(Button btn) {
              //second btn has been clicked
            }
        });
```

3-options
```xml
same as usual but ends with First for the 1st button and Second for the 2nd button
        textFirst        
        backgroundColorFirst
        backgroundResFirst        
        textSizeFirst
        textColorFirst
        fontStyleFirst
        gravityFirst
        drawableRightFirst        
        drawableStartFirst        
        drawableLeftFirst        
        drawableEndFirst        
        drawableTopFirst        
        drawableBottomFirst        
        drawablePaddingFirst
        textSecond        
        backgroundColorSecond
        backgroundResSecond        
        textSizeSecond
        textColorSecond
        fontStyleSecond
        gravitySecond
        drawableRightSecond        
        drawableStartSecond        
        drawableLeftSecond        
        drawableEndSecond        
        drawableTopSecond        
        drawableBottomSecond        
        drawablePaddingSecond
        clickableSecond
        duration //animation duration
        dualDirection //animation direction rtl/ltr
        only basic options
        for more use dualBtn.getFirstButton() dualBtn.getSecondButton()
```

## Author
Basel Horany 
http://baselhorany.com

