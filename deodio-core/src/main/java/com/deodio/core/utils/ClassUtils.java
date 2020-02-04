package com.deodio.core.utils;

import com.deodio.core.utils.Utils;

public class ClassUtils extends Utils{

    public static boolean equalClass(Class cls,Class target){
        return cls.isAssignableFrom(target);
    }


    public static boolean equalObject(Object obj,Class target){
        return equalClass(obj.getClass(),target);
    }


}
