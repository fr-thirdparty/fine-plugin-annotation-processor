
### 注解处理器主要参数
pluginVersion
pluginXmlDir
pluginFineVersion


### 接口注解定义规则

1. 注解名域接口名一致，方便识别调用
2. 使用@Module注解，已标记接口属于哪个模块
3. 使用@EnabledSupportedAnnotation注解，启用处理逻辑，否则注解处理器不会处理相关标记
4. 注解具体内容依据接口进行定义

  
      @Retention(RetentionPolicy.SOURCE)
      @Target({ElementType.TYPE})
      @Module(FineModule.CORE)
      @EnabledSupportedAnnotation
      public @interface LocaleFinder {
      
          /**
           * 国际化实现类
           *
           * @return Class<?>[]
           */
          Class<?>[] value() default {};
      
      }
      
### 注解处理逻辑实现

1. 实现 IAnnotationProcessor 接口,实际继承 BaseAnnotationProcessor 类即可
2. 使用@Component注解，将实现类注入处理容器

### 注解处理器使用方法
1. 使用项目依赖当前项目
2. 配置编译参数
3. 