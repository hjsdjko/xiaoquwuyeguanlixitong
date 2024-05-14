
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
 * 房屋
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fangwu")
public class FangwuController {
    private static final Logger logger = LoggerFactory.getLogger(FangwuController.class);

    @Autowired
    private FangwuService fangwuService;


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
        PageUtils page = fangwuService.queryPage(params);

        //字典表数据转换
        List<FangwuView> list =(List<FangwuView>)page.getList();
        for(FangwuView c:list){
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
        FangwuEntity fangwu = fangwuService.selectById(id);
        if(fangwu !=null){
            //entity转view
            FangwuView view = new FangwuView();
            BeanUtils.copyProperties( fangwu , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(fangwu.getYonghuId());
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
    public R save(@RequestBody FangwuEntity fangwu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fangwu:{}",this.getClass().getName(),fangwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            fangwu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<FangwuEntity> queryWrapper = new EntityWrapper<FangwuEntity>()
            .eq("yonghu_id", fangwu.getYonghuId())
            .eq("fangwu_uuid_number", fangwu.getFangwuUuidNumber())
            .eq("fangwu_loudong", fangwu.getFangwuLoudong())
            .eq("fangwu_danyuan", fangwu.getFangwuDanyuan())
            .eq("fangwu_fanghao", fangwu.getFangwuFanghao())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangwuEntity fangwuEntity = fangwuService.selectOne(queryWrapper);
        if(fangwuEntity==null){
            fangwu.setInsertTime(new Date());
            fangwu.setCreateTime(new Date());
            fangwuService.insert(fangwu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FangwuEntity fangwu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fangwu:{}",this.getClass().getName(),fangwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            fangwu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<FangwuEntity> queryWrapper = new EntityWrapper<FangwuEntity>()
            .notIn("id",fangwu.getId())
            .andNew()
            .eq("yonghu_id", fangwu.getYonghuId())
            .eq("fangwu_uuid_number", fangwu.getFangwuUuidNumber())
            .eq("fangwu_loudong", fangwu.getFangwuLoudong())
            .eq("fangwu_danyuan", fangwu.getFangwuDanyuan())
            .eq("fangwu_fanghao", fangwu.getFangwuFanghao())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FangwuEntity fangwuEntity = fangwuService.selectOne(queryWrapper);
        if(fangwuEntity==null){
            fangwuService.updateById(fangwu);//根据id更新
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
        fangwuService.deleteBatchIds(Arrays.asList(ids));
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
            List<FangwuEntity> fangwuList = new ArrayList<>();//上传的东西
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
                            FangwuEntity fangwuEntity = new FangwuEntity();
//                            fangwuEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            fangwuEntity.setFangwuUuidNumber(data.get(0));                    //房屋编号 要改的
//                            fangwuEntity.setFangwuLoudong(data.get(0));                    //楼栋 要改的
//                            fangwuEntity.setFangwuDanyuan(data.get(0));                    //单元 要改的
//                            fangwuEntity.setFangwuFanghao(data.get(0));                    //房号 要改的
//                            fangwuEntity.setFangwuContent("");//详情和图片
//                            fangwuEntity.setInsertTime(date);//时间
//                            fangwuEntity.setCreateTime(date);//时间
                            fangwuList.add(fangwuEntity);


                            //把要查询是否重复的字段放入map中
                                //房屋编号
                                if(seachFields.containsKey("fangwuUuidNumber")){
                                    List<String> fangwuUuidNumber = seachFields.get("fangwuUuidNumber");
                                    fangwuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fangwuUuidNumber = new ArrayList<>();
                                    fangwuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fangwuUuidNumber",fangwuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //房屋编号
                        List<FangwuEntity> fangwuEntities_fangwuUuidNumber = fangwuService.selectList(new EntityWrapper<FangwuEntity>().in("fangwu_uuid_number", seachFields.get("fangwuUuidNumber")));
                        if(fangwuEntities_fangwuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FangwuEntity s:fangwuEntities_fangwuUuidNumber){
                                repeatFields.add(s.getFangwuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [房屋编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fangwuService.insertBatch(fangwuList);
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
