package cakar.sfgrv.sfgredisvaadin.utilsAbstract;

import java.util.List;
import java.util.Set;

public class BaseDefault {

     public final static <T> boolean checkNull(T value){
        if(value == null){
            return true;
        }
        return false;
    }
    public final static <T> boolean checkNullStr(T value){
        if(value == ""){
            return true;
        }
        return false;
    }
    public final static <T> boolean isNotEmptyList(List<T> list){
        return  !list.isEmpty();
    }
    public  final static <T> boolean isEmptyList(List<T> list){
       return !list.isEmpty();
    }
    public final static <T> boolean isNotEmptySet(Set<T> list){
        if(list.isEmpty()){
            return false;
        }
        return true;
    }
    public final static <T> boolean isEmptySet(Set<T> list){
        if(!list.isEmpty()){
            return false;
        }
        return true;
    }
}
