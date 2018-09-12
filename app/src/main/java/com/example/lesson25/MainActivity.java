package com.example.lesson25;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // Переменные для хранения ссылок кнопок для запуска соответствующих анимаций
    Button rotateX;
    Button rotateY;
    Button rotateLeft;
    Button rotateRight;
    Button translateX;
    Button translateY;
    Button fadeIn;
    Button fadeOut;
    Button zoomIn;
    Button zoomOut;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Получение ссылок к переменным в файле макета
        image = findViewById(R.id.image);

        rotateX = findViewById(R.id.rotateX);
        rotateY = findViewById(R.id.rotateY);
        rotateLeft = findViewById(R.id.rotateLeft);
        rotateRight = findViewById(R.id.rotateRight);
        translateX = findViewById(R.id.translateX);
        translateY = findViewById(R.id.translateY);
        fadeIn = findViewById(R.id.fadeIn);
        fadeOut = findViewById(R.id.fadeOut);
        zoomIn = findViewById(R.id.zoomIn);
        zoomOut = findViewById(R.id.zoomOut);


    }


    // Функция служит для создания анимаций и их запуска в определенной очереди
    public void animatorSet(View view) {
        ValueAnimator rotateLeft = ValueAnimator.ofFloat(image.getRotation(), image.getRotation() - 45);
        rotateLeft.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setRotation((float) animation.getAnimatedValue());
            }
        });

        ValueAnimator fi = ValueAnimator.ofFloat(0, 1);
        fi.setDuration(500).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setAlpha((float) animation.getAnimatedValue());
            }
        });

        ValueAnimator zi = ValueAnimator.ofFloat(1, 3);
        zi.setDuration(3000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setScaleX((float) animation.getAnimatedValue());
                image.setScaleY((float) animation.getAnimatedValue());
            }
        });

        ValueAnimator rY = ValueAnimator.ofInt(0, 360);
        rY.setRepeatMode(ValueAnimator.RESTART);
        rY.setRepeatCount(5);
        rY.setDuration(1000);
        rY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                image.setRotationY((int) animation.getAnimatedValue());
            }
        });

        // Класс AnimatorSet служит для создания очереди анимаций
        // Запускать анимации можно одновременно (with), одну анимацию до другой (before),
        // одну анимацию после другой анимации или после указанного интервала времени (after)
        AnimatorSet animations = new AnimatorSet();
        animations.play(fi).with(zi);
        animations.play(zi).with(rotateLeft);
        animations.play(rY).after(3000);
        animations.start();
    }

    // Функция установлена в качестве значения атрибута onclick для кнопок в файле макета
    public void animateOnClick(View view) {

        // Перебрать id нажатой кнопки для определения какая кнопка была нажата
        switch (view.getId()) {
            // Если id нажатой кнопки равен id кнопки вращения по оси Х
            case R.id.rotateX:
                // Применить анимацию вращения по оси Х равную 360 градусам с продолжительностью 1000 миллисекунд (1 секунда)
                // и установить слушатель анимации, который имеет функции вызывающиеся при запуске, окончании и повторе анимации
                // также для анимации устанавливается интерполятор LinearInterpolator, который меняет значение линейно (без скачков)
                image.animate().rotationX(360).setDuration(1000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        image.setRotationX(0);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).setInterpolator(new LinearInterpolator()).start();
                break;
            // Анимация для вращения по оси Y
            case R.id.rotateY:
                // Начиная от этой анимации используется другой метод анимирования, который отличается
                // механизмом анимирования. ValueAnimator анимирует не виджет а только указанные значения
                // затем эти значения могут быть использованы как угодно, в данном примере анимированные
                // значения служат для применения анимации объекту image, значения анимируются от 0 до 360
                // setRepeatMode - устанавливает режим повтора, ValueAnimator.RESTART указывает, что после
                // изменения значения от 0 до 360 значение обнулялось и снова менялось от 0 до 360
                // ValueAnimator.REVERSE - указывает что значение должно меняться с 0 до 360 и обратно с 360 до 0
                // setRepeatCount - устанавливает количество повторов анимации
                // setDuration - устанавливает продолжительность анимации, длиной в 1 секунду (1000 миллисекунд)
                // addUpdateListener - устанавливает обработчик для события обновления анимированного значения,
                // затем в функции onAnimationUpdate получив анимированное значение устанавливается новое значение для
                // вращения объекта image по оси Y.
                // Затем через функцию start запускается анимация.
                ValueAnimator va = ValueAnimator.ofInt(0, 360);
                va.setRepeatMode(ValueAnimator.RESTART);
                va.setRepeatCount(5);
                va.setDuration(1000);
                va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setRotationY((int) animation.getAnimatedValue());
                    }
                });
                va.start();
                break;
            // Код для кнопки вращения влево
            case R.id.rotateLeft:
                // В данном случае получаем значение текущего вращения объекта по оси Z, затем
                // для вращения влево надо указать текущее значение - 45 градусов
                // текущее значение вращения получаем через функцию image.getRotation()
                ValueAnimator rotateLeft = ValueAnimator.ofFloat(image.getRotation(), image.getRotation() - 45);
                rotateLeft.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setRotation((float) animation.getAnimatedValue());
                    }
                });
                rotateLeft.start();
                break;
            // Код для кнопки вращения вправо
            case R.id.rotateRight:
                // Код аналогичен коду вращения влево, единственно отличается тем что надо
                // указать текущее значение вращения + 45 градусов
                ValueAnimator rotateRight = ValueAnimator.ofFloat(image.getRotation(), image.getRotation() + 45);
                rotateRight.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setRotation((float) animation.getAnimatedValue());
                    }
                });
                rotateRight.start();
                break;
            // Анимация translate служит для изменения местоположения объекта по указанной оси
            case R.id.translateX:
                // Создать объект анимации для анимирования значения перемещения объекта по оси X
                // меняет значение оси X с нуля до 200. 0 - текущее местоположение.
                ValueAnimator tX = ValueAnimator.ofFloat(0, 200);
                tX.setRepeatMode(ValueAnimator.REVERSE);
                tX.setRepeatCount(1);
                tX.setInterpolator(new AnticipateInterpolator());
                tX.setDuration(250).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setTranslationX((float) animation.getAnimatedValue());
                    }
                });
                tX.start();
                break;
            case R.id.translateY:
                ValueAnimator tY = ValueAnimator.ofFloat(0, 400);
                tY.setRepeatMode(ValueAnimator.REVERSE);
                tY.setRepeatCount(1);
                tY.setInterpolator(new BounceInterpolator());
                tY.setDuration(1000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setTranslationY((float) animation.getAnimatedValue());
                    }
                });
                tY.start();
                break;
            //  Анимация для плавного появления
            case R.id.fadeIn:
                // Анимирование плавного появления и исчезновения достигается
                // путём изменения значения alpha (прозрачности) объекта
                // setAlpha(0) - делает объект невидимым
                // setAlpha(1) - делает объект полностью видимым
                // полупрозрачность устанавливается значением больше 0 и меньше 1, например 0.5
                ValueAnimator fi = ValueAnimator.ofFloat(0, 1);
                fi.setDuration(500).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                fi.start();
                break;
            // Анимация для плавного исчезновения
            // меняет значение объекта с непрозрачного 1 до прозрачного 0
            case R.id.fadeOut:
                ValueAnimator fo = ValueAnimator.ofFloat(1, 0);
                fo.setDuration(500).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                fo.start();
                break;
            // Увеличение и уменьшение размеров объекта
            case R.id.zoomIn:
                // Увеличение и уменьшение размеров объекта указывается через
                // масштаб - scale объекта, по умолчанию объект имеет масштаб равный 1
                // в данной анимации масштаб увеличивается с 1 до 3, точнее в три раза становится больше
                // в качестве интерполятора используется CycleInterpolator, который циклически меняет
                // значение с 1 до 3, затем обратно и повторяет это 3 раза
                // для пропорционального изменения масштаба необходимо установить масштаб
                // как для оси Х так и для Y
                ValueAnimator zi = ValueAnimator.ofFloat(1, 3);
                zi.setInterpolator(new CycleInterpolator(3));
                zi.setDuration(3000).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setScaleX((float) animation.getAnimatedValue());
                        image.setScaleY((float) animation.getAnimatedValue());
                    }
                });
                zi.start();
                break;
            // Уменьшение масштаба объекта
            case R.id.zoomOut:
                // Для отдаления объекта используется уменьшение масштаба
                // можно использовать значения меньше 1, например 0.5 (половина масштаба)
                // 0.25 (четверть масштаба) и т.д.
                ValueAnimator zo = ValueAnimator.ofFloat(3, 1);
                zo.setDuration(300).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        image.setScaleX((float) animation.getAnimatedValue());
                        image.setScaleY((float) animation.getAnimatedValue());
                    }
                });
                zo.start();
                break;
        }
    }
}
