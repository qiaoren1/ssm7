package com.cm.service.impl;

import com.cm.entity.SysParam;
import com.cm.mapper.BaseMapper;
import com.cm.mapper.SysParamMapper;
import com.cm.mapper.SysRoleMapper;
import com.cm.mapper.SysUserMapper;
import com.cm.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2020/5/8.
 */
public class BaseServiceImpl<T> implements BaseService<T>{

    BaseMapper<T> baseMapper;

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysParamMapper sysParamMapper;

    //在构建父类完成后，baseMapper需要有对应的相关子Mapper的对象作为引用
    @PostConstruct//在构造方法后，初始数据的处理
    private void initBaseMapper() throws Exception{
        //完成以下逻辑，需要对研发本身进行命名与使用规范
        //this关键字指对象本身，这里指的是调用此方法的实现类（子类）
        System.out.println("=======this :"+this);
        System.out.println("=======父类基本信息："+this.getClass().getSuperclass());
        System.out.println("=======父类和泛型的信息："+this.getClass().getGenericSuperclass());

        ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
        //获取第一个参数的class
        Class clazz = (Class)type.getActualTypeArguments()[0];//SysUser
        System.out.println("=======class:"+clazz);
        //转化为属性名（相关的Mapper子类的引用名） sysUserMapper
        String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper";
        System.out.println("=======localField:"+localField);
        //getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
        Field field=this.getClass().getSuperclass().getDeclaredField(localField);
        System.out.println("=======field:"+field);
        System.out.println("=======field对应的对象:"+field.get(this));
        Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");

        System.out.println("=======baseField:"+baseField);
        System.out.println("=======baseField对应的对象:"+baseField.get(this));
        //field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中
        baseField.set(this, field.get(this));
        System.out.println("========baseField对应的对象:"+baseMapper);

    }

    @Override
    public <K> int deleteByPrimaryKey(K id) {
        return 0;
    }

    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return 0;
    }

    @Override
    public <K> T selectByPrimaryKey(K id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return 0;
    }

    //分页查询包括数据列表、总记录数等信息
    @Override
    public PageInfo<T> selectPage(T entity, Integer pageIndex, Integer pageSize) {
        //startPage：设置的分页查询条件，它只起作用于接下来的第一条执行的sql
        PageHelper.startPage(pageIndex, pageSize);
        //查询数据
        List<T> list = baseMapper.selectPage(entity);
        //封装查询信息，便于使用
        PageInfo<T> pageInfo = new PageInfo<T>(list);

        return pageInfo;
    }

    public Map<String, Object> loadSysParam() {
        //用来保存系统所有字段的参数
        Map<String, Object> sysParamMap = new HashMap<String, Object>();
        //用来存放某个字段的参数
        Map<String, Object> fieldMap = null;

        List<SysParam> sysParams = sysParamMapper.selectList();
        System.out.println("sysParams = "+sysParams);
        for (SysParam sysParam : sysParams) {
            //   if("1".equals(sysParam.getSysParamType())){
            //sys_param_type为1的存的是sql语句，需要把sql语句重新进行查询
/*                    String sql = sysParam.getSysParamValue();
                    System.out.println("=======================================sql:"+sql);
                    List<SysParam> otherSysParams = sysParamMapper.selectOtherTable(sql);
                    //系统参数的map没有相关字段的map,创建一个
                    fieldMap = new LinkedHashMap<String, Object>();
                    //把通过sql查询出来的参数（因一条sql使用同一个字段）全部放到fieldMap
                    for (SysParam sysParam2 : otherSysParams) {
                        //把对应的key和value(显示文本)存放字段的map里
                        fieldMap.put(sysParam2.getSysParamValue(), sysParam2.getSysParamText());
                    }
                    //把字段map参数存到系统参数map
                    sysParamMap.put(sysParam.getSysParamField(), fieldMap);*/

            //  }else{

            if(null==sysParamMap.get(sysParam.getSysParamField())){
                System.out.println("sysParamMap.get(sysParam.getSysParamField()= "+sysParamMap.get(sysParam.getSysParamField()));
                //系统参数的map没有相关字段的map,就创建一个
                fieldMap = new LinkedHashMap<String, Object>();
                //把对应的key和value(显示文本)存放字段的map里
                fieldMap.put(sysParam.getSysParamValue(), sysParam.getSysParamText());
                //把字段的map存到系统参数的map里，下次再取时就存在字段map了
                sysParamMap.put(sysParam.getSysParamField(), fieldMap);
            }else{
                //系统参数的map已经有相关字段的map,就直接使用
                //fieldMap =(Map<String, Object>) sysParamMap.get(sysParam.getSysParamField());
                //System.out.println("fieldMap = "+fieldMap);
                //把对应的key和value(显示文本)存放字段的map里
                fieldMap.put(sysParam.getSysParamValue(), sysParam.getSysParamText());
               // System.out.println("fieldMap.put = "+fieldMap);
                //把字段的map存到系统参数的map里，下次再取时就存在字段map了
                //sysParamMap.put(sysParam.getSysParamField(), fieldMap);
            }
        }
        // }
        return sysParamMap;
    }

}



