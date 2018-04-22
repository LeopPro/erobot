package cn.edu.csust.liman.erobot.sender.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static class LazyLoader<T> {
        private T t;
        private Class<T> clazz;

        private LazyLoader(Class<T> clazz) {
            this.clazz = clazz;
        }

        public T get() {
            if (t == null) {
                synchronized (BeanUtil.class){
                    if(t==null){
                        t = BeanUtil.getBean(clazz);
                    }
                }
            }
            return t;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> LazyLoader<T> getLazyLoader(Class<T> clazz) {
        return new LazyLoader<>(clazz);
    }
}
