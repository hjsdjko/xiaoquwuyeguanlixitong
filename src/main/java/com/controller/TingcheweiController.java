
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
 * 停车位
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tingchewei")
public class TingcheweiController {
    private static final Logger logger = LoggerFactory.getLogger(TingcheweiController.class);

    @Autowired
    private TingcheweiService tingcheweiService;


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
        PageUtils page = tingcheweiService.queryPage(params);

        //字典表数据转换
        List<TingcheweiView> list =(List<TingcheweiView>)page.getList();
        for(TingcheweiView c:list){
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
        TingcheweiEntity tingchewei = tingcheweiService.selectById(id);
        if(tingchewei !=null){
            //entity转view
            TingcheweiView view = new TingcheweiView();
            BeanUtils.copyProperties( tingchewei , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(tingchewei.getYonghuId());
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
    public R save(@RequestBody TingcheweiEntity tingchewei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tingchewei:{}",this.getClass().getName(),tingchewei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            tingchewei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<TingcheweiEntity> queryWrapper = new EntityWrapper<TingcheweiEntity>()
            .eq("yonghu_id", tingchewei.getYonghuId())
            .eq("tingchewei_uuid_number", tingchewei.getTingcheweiUuidNumber())
            .eq("tingchewei_address", tingchewei.getTingcheweiAddress())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TingcheweiEntity tingcheweiEntity = tingcheweiService.selectOne(queryWrapper);
        if(tingcheweiEntity==null){
            tingchewei.setInsertTime(new Date());
            tingchewei.setCreateTime(new Date());
            tingcheweiService.insert(tingchewei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TingcheweiEntity tingchewei, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,tingchewei:{}",this.getClass().getName(),tingchewei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            tingchewei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<TingcheweiEntity> queryWrapper = new EntityWrapper<TingcheweiEntity>()
            .notIn("id",tingchewei.getId())
            .andNew()
            .eq("yonghu_id", tingchewei.getYonghuId())
            .eq("tingchewei_uuid_number", tingchewei.getTingcheweiUuidNumber())
            .eq("tingchewei_address", tingchewei.getTingcheweiAddress())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TingcheweiEntity tingcheweiEntity = tingcheweiService.selectOne(queryWrapper);
        if(tingcheweiEntity==null){
            tingcheweiService.updateById(tingchewei);//根据id更新
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
        tingcheweiService.deleteBatchIds(Arrays.asList(ids));
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
            List<TingcheweiEntity> tingcheweiList = new ArrayList<>();//上传的东西
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
                            TingcheweiEntity tingcheweiEntity = new TingcheweiEntity();
//                            tingcheweiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            tingcheweiEntity.setTingcheweiUuidNumber(data.get(0));                    //停车位编号 要改的
//                            tingcheweiEntity.setTingcheweiAddress(data.get(0));                    //停车位位置 要改的
//                            tingcheweiEntity.setTingcheweiContent("");//详情和图片
//                            tingcheweiEntity.setInsertTime(date);//时间
//                            tingcheweiEntity.setCreateTime(date);//时间
                            tingcheweiList.add(tingcheweiEntity);


                            //把要查询是否重复的字段放入map中
                                //停车位编号
                                if(seachFields.containsKey("tingcheweiUuidNumber")){
                                    List<String> tingcheweiUuidNumber = seachFields.get("tingcheweiUuidNumber");
                                    tingcheweiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tingcheweiUuidNumber = new ArrayList<>();
                                    tingcheweiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tingcheweiUuidNumber",tingcheweiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //停车位编号
                        List<TingcheweiEntity> tingcheweiEntities_tingcheweiUuidNumber = tingcheweiService.selectList(new EntityWrapper<TingcheweiEntity>().in("tingchewei_uuid_number", seachFields.get("tingcheweiUuidNumber")));
                        if(tingcheweiEntities_tingcheweiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TingcheweiEntity s:tingcheweiEntities_tingcheweiUuidNumber){
                                repeatFields.add(s.getTingcheweiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [停车位编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tingcheweiService.insertBatch(tingcheweiList);
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
