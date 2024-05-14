
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 投诉
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tousu")
public class TousuController {
    private static final Logger logger = LoggerFactory.getLogger(TousuController.class);

    @Autowired
    private TousuService tousuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;



    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = tousuService.queryPage(params);

        //字典表数据转换
        List<TousuView> list =(List<TousuView>)page.getList();
        for(TousuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        TousuEntity tousu = tousuService.selectById(id);
        if(tousu !=null){
            //entity转view
            TousuView view = new TousuView();
            BeanUtils.copyProperties( tousu , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(tousu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody TousuEntity tousu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tousu:{}",this.getClass().getName(),tousu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role)){
            tousu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            tousu.setChuliTypes(1);
        }

        Wrapper<TousuEntity> queryWrapper = new EntityWrapper<TousuEntity>()
            .eq("yonghu_id", tousu.getYonghuId())
            .eq("tousu_uuid_number", tousu.getTousuUuidNumber())
            .eq("tousu_name", tousu.getTousuName())
            .eq("tousu_types", tousu.getTousuTypes())
            .eq("chuli_types", tousu.getChuliTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TousuEntity tousuEntity = tousuService.selectOne(queryWrapper);
        if(tousuEntity==null){
            tousu.setInsertTime(new Date());
            tousu.setCreateTime(new Date());
            tousuService.insert(tousu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TousuEntity tousu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tousu:{}",this.getClass().getName(),tousu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            tousu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<TousuEntity> queryWrapper = new EntityWrapper<TousuEntity>()
            .notIn("id",tousu.getId())
            .andNew()
            .eq("yonghu_id", tousu.getYonghuId())
            .eq("tousu_uuid_number", tousu.getTousuUuidNumber())
            .eq("tousu_name", tousu.getTousuName())
            .eq("tousu_types", tousu.getTousuTypes())
            .eq("chuli_types", tousu.getChuliTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TousuEntity tousuEntity = tousuService.selectOne(queryWrapper);
        if(tousuEntity==null){
            tousuService.updateById(tousu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        tousuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<TousuEntity> tousuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            TousuEntity tousuEntity = new TousuEntity();
//                            tousuEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            tousuEntity.setTousuUuidNumber(data.get(0));                    //投诉编号 要改的
//                            tousuEntity.setTousuName(data.get(0));                    //投诉名称 要改的
//                            tousuEntity.setTousuTypes(Integer.valueOf(data.get(0)));   //投诉类型 要改的
//                            tousuEntity.setTousuContent("");//详情和图片
//                            tousuEntity.setChuliTypes(Integer.valueOf(data.get(0)));   //是否处理 要改的
//                            tousuEntity.setChuliContent("");//详情和图片
//                            tousuEntity.setInsertTime(date);//时间
//                            tousuEntity.setCreateTime(date);//时间
                            tousuList.add(tousuEntity);


                            //把要查询是否重复的字段放入map中
                                //投诉编号
                                if(seachFields.containsKey("tousuUuidNumber")){
                                    List<String> tousuUuidNumber = seachFields.get("tousuUuidNumber");
                                    tousuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tousuUuidNumber = new ArrayList<>();
                                    tousuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tousuUuidNumber",tousuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //投诉编号
                        List<TousuEntity> tousuEntities_tousuUuidNumber = tousuService.selectList(new EntityWrapper<TousuEntity>().in("tousu_uuid_number", seachFields.get("tousuUuidNumber")));
                        if(tousuEntities_tousuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TousuEntity s:tousuEntities_tousuUuidNumber){
                                repeatFields.add(s.getTousuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [投诉编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tousuService.insertBatch(tousuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
