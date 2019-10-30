package com.example.hotel.Controller;

import com.example.hotel.DTO.ObjectDTO;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.model.Goods;
import com.example.hotel.model.GoodsOrder;
import com.example.hotel.model.User;
import com.example.hotel.service.GoodsOrderService;
import com.example.hotel.service.GoodsService;
import com.example.hotel.service.UserService;
import com.example.hotel.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
public class GradeController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsOrderService goodsOrderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/api/GradeExchang")
    public ResultDTO exchange(GoodsOrder goodsOrder){
        ResultDTO resultDTO=new ResultDTO();
        try {
            User user = userService.selectByPrimaryKey(goodsOrder.getUid());
            Goods goods =goodsService.selectByPrimaryKey(goodsOrder.getGid());
            if(user.getUgrade()>goods.getGrade()){
                user.setUgrade(user.getUgrade()-goods.getGrade());
            }
            else {
                return resultDTO.unkonwFail("积分不够");
            }
            userService.saveOrUpdate(user);
            int flag=goodsOrderService.insertSelective(goodsOrder);
            if(flag==0){
                return resultDTO.fail("积分订单创建失败");
            }
            return resultDTO.ok(null);

        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    /**
     * 订单编辑部分
     * @param currPage
     * @param pageSize
     * @return
     */

    @RequestMapping("/api/back/selGoodsOrder")
    public ResultDTO selGoodsOrder(int currPage,int pageSize){
        ResultDTO resultDTO=new ResultDTO();
        try {
            int total=goodsOrderService.selectCount();
            if((currPage-1)*pageSize>total){
                return resultDTO.ok(null);
            }
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize,total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<GoodsOrder> goodsOrders=goodsOrderService.selectPage(parameter);
            if(goodsOrders.size()==0){
                return resultDTO.fail();
            }
            ObjectDTO object=new ObjectDTO(total,goodsOrders);
            return resultDTO.ok(object);

        }catch (org.springframework.jdbc.BadSqlGrammarException e){
            return resultDTO.fail();
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/selGoodsOrder")
    public ResultDTO selUserGoodsOrder(int currPage,int pageSize,String uid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            int total=goodsOrderService.selectCount();
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize,total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            Integer begin= peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize();
            Integer num= peoplePageBean.getPageSize();
            List<GoodsOrder> goodsOrders=goodsOrderService.selectByUidPage(begin,num,uid);
            if(goodsOrders.size()==0){
                return resultDTO.fail();
            }
            ObjectDTO object=new ObjectDTO(total,goodsOrders);
            return resultDTO.ok(object);

        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/editGoodsOrder")
    public ResultDTO editGoodsOrder(GoodsOrder goodsOrder){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(goodsOrder.getGorderid()==null){
                return resultDTO.nothing();
            }
            int flag=goodsOrderService.updateByPrimaryKeySelective(goodsOrder);
            if(flag==0){
                return resultDTO.fail("商品订单修改失败");
            }
            return resultDTO.ok(null);

        } catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/deleteGoodsOrder")
    public ResultDTO deleteGoodsOrder(Integer gorderid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(gorderid==null){
                return resultDTO.nothing();
            }
            int flag=goodsOrderService.deleteByPrimaryKey(gorderid);
            if(flag==0){
                return resultDTO.fail("商品订单数据库删除失败");
            }
            return resultDTO.ok(null);

        } catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }


    /**
     * 商品部分
     * @param currPage
     * @param pageSize
     * @return
     */

    @RequestMapping("/api/selGoods")
    public ResultDTO selGoods(int currPage,int pageSize){
        ResultDTO resultDTO=new ResultDTO();
        try {
            int total= goodsService.selectCount();
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize,total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<Goods> goods=goodsService.selectPage(parameter);
            if(goods.size()==0){
                return resultDTO.fail();
            }
            ObjectDTO object=new ObjectDTO(total,goods);
            return resultDTO.ok(object);

        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }


    @RequestMapping("/api/back/addGoods")
    public ResultDTO addGoods(Goods goods){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(goods.getGname()==null){
                return resultDTO.nothing();
            }
            int flag=goodsService.insertSelective(goods);
            if(flag==0){
                return resultDTO.fail("商品数据库插入失败");
            }
            return resultDTO.ok(null);

        } catch (org.springframework.dao.DuplicateKeyException e){
            return resultDTO.interFail("商品");
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/editGoods")
    public ResultDTO editGoods(Goods goods){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(goods.getGid()==null){
                return resultDTO.nothing();
            }
            int flag=goodsService.updateByPrimaryKeySelective(goods);
            if(flag==0){
                return resultDTO.fail("商品数据库更新失败");
            }
            return resultDTO.ok(null);

        } catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }

    }

    @RequestMapping("/api/back/deleteGoods")
    public ResultDTO deleteGoods(Integer gid){
        ResultDTO resultDTO=new ResultDTO();
        try {
            if(gid==null){
                return resultDTO.nothing();
            }
            int flag=goodsService.deleteByPrimaryKey(gid);
            if(flag==0){
                return resultDTO.fail("商品数据库删除失败");
            }
            return resultDTO.ok(null);

        } catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }
    }

}
