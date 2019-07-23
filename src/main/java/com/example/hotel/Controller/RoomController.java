package com.example.hotel.Controller;

import com.example.hotel.DTO.ObjectDTO;
import com.example.hotel.DTO.ResultDTO;
import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.model.Room;
import com.example.hotel.model.RoomOrder;
import com.example.hotel.model.TroRoom;
import com.example.hotel.service.RoomOrderService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.TroRoomService;
import com.example.hotel.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoomController {

    @Autowired
    private RoomOrderService roomOrderService;
    @Autowired
    private TroRoomService troRoomService;
    @Autowired
    private RoomService roomService;

    /*
    @RequestMapping("/api/getallorder")
    public ResultDTO getAllOrder(int currPage, int pageSize){   //所有订单，包括失效完成

        ResultDTO resultDTO=new ResultDTO();
        try {
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize, roomOrderService.selectCount());
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
        List<RoomOrder> data =new ArrayList<RoomOrder>();
        data = roomOrderService.selectAllOrder(parameter);

            if(data==null){
                return resultDTO.fail();
            }
            return resultDTO.ok(data);
        }catch (Exception e){
            return resultDTO.unkonwFail(e.toString());
        }

    }
    */
    @RequestMapping("/api/back/getallpayorder")
    public ResultDTO getAllPayOrder(int currPage, int pageSize) {  //所有已成功付款待处理订单
        ResultDTO resultDTO = new ResultDTO();
        try {

            int total=roomOrderService.selectPayCount();
            if((currPage-1)*pageSize>total){
                return resultDTO.ok(null);
            }
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize, total);
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<RoomOrder> data = new ArrayList<RoomOrder>();

            data = roomOrderService.selectAllPayOrder(parameter);
            if (data == null) {
                return resultDTO.fail();
            }
            ObjectDTO object=new ObjectDTO(total,data);
            return resultDTO.ok(object);
        } catch (org.springframework.jdbc.BadSqlGrammarException e){
            return resultDTO.fail();
        }catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }

    @RequestMapping("/api/back/disposeorder")  //客人到店后，处理订单
    public ResultDTO disposeOrder(   RoomOrder roomOrder) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            if (roomOrder != null && roomOrder.getOrderstatus() == 2) {
                roomOrder.setOrderstatus(4); //设为4，表明已经处理
                int w = roomOrderService.updateByPrimaryKeySelective(roomOrder);
                if (w == 0)
                    return resultDTO.fail("数据库插入失败");
                return resultDTO.ok(null);
            }
            return resultDTO.fail("接收数据为空或状态有误");
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }


    }

    @RequestMapping("/api/back/getallcompleteorder")
    public ResultDTO getAllCompleteOrder(int currPage, int pageSize) {   //所有已经完成订单
        ResultDTO resultDTO = new ResultDTO();

        try {
            int total=roomOrderService.selectCount();
            if((currPage-1)*pageSize>total){
                return resultDTO.ok(null);
            }
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize, total);
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<RoomOrder> data = new ArrayList<RoomOrder>();
            data = roomOrderService.selectAllCompleteOrder(parameter);

            ObjectDTO object=new ObjectDTO(total,data);
            return resultDTO.ok(object);
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }


    @RequestMapping("/api/getallroom")        //返回所有房间信息
    public ResultDTO getAllRoom(int currPage,int pageSize) {

        ResultDTO resultDTO = new ResultDTO();

        try {
            int total=roomService.selectCount();
            if((currPage-1)*pageSize>total){
                return resultDTO.ok(null);
            }
            //创建当前页的分页对象，计算四个参数
            PageUtil peoplePageBean = new PageUtil(currPage, pageSize,total);
            /*-------------------向数据库中查询当前页的数据-------------------*/
            Map<String, Integer> parameter = new HashMap<>(2);
            parameter.put("begin", peoplePageBean.getCurrPage() * peoplePageBean.getPageSize() - peoplePageBean.getPageSize());
            parameter.put("num", peoplePageBean.getPageSize());
            List<TroRoom> troRooms = new ArrayList<>();
            troRooms = troRoomService.selectRoomPage(parameter);
            if (troRooms != null) {
                ObjectDTO objectDTO=new ObjectDTO(total,troRooms);
                return resultDTO.ok(objectDTO);
            }
            return resultDTO.fail();
        } catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }
    }





    @RequestMapping("/api/back/addroom") //增加房间类型
    public ResultDTO insertRoom(  RoomDTO roomDTO) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            if(roomDTO.getRoomintroduce()==null){
                return resultDTO.nothing();
            }
            if (roomDTO != null && roomDTO.getRoomintroduce() != null) {
                TroRoom troRoom = new TroRoom();
                troRoom.setRoomintroduce(roomDTO.getRoomintroduce());
                troRoom.setRoomarea(roomDTO.getRoomarea());
                troRoom.setRoombed(roomDTO.getRoombed());
                troRoom.setRoombreakfast(roomDTO.getRoombreakfast());
                troRoom.setRoomimage(roomDTO.getRoomimage());
                troRoom.setRoomnum(roomDTO.getRoomnum());
                troRoom.setRoomwifi(roomDTO.getRoomwifi());
                troRoom.setRoomprice(roomDTO.getRoomprice());
               int x= troRoomService.insertSelective(troRoom);   //插入

                Room room = new Room();
                room.setRoomname(roomDTO.getRoomintroduce());
                room.setRoomnumber(roomDTO.getRoomnumber());
                room.setRoomprice(roomDTO.getRoomprice());
                int y=roomService.insertSelective(room);   //插入
                if(x==0 && y==0)
                    return resultDTO.fail("插入失败");
                return resultDTO.ok(null);

            }
            return resultDTO.fail("接收数据为空");
        } catch (org.springframework.dao.DuplicateKeyException e){
            return resultDTO.interFail("房间");
        }catch (Exception e) {
            return resultDTO.unkonwFail(e.toString());
        }

    }

    @RequestMapping("api/back/updateroom") //更新
    public ResultDTO updateRoom(   RoomDTO roomDTO) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            if (roomDTO != null && roomDTO.getRoomintroduce() != null) {
                TroRoom troRoom = new TroRoom();
                troRoom.setRoomintroduce(roomDTO.getRoomintroduce());
                troRoom.setRoomarea(roomDTO.getRoomarea());
                troRoom.setRoombed(roomDTO.getRoombed());
                troRoom.setRoombreakfast(roomDTO.getRoombreakfast());
                troRoom.setRoomimage(roomDTO.getRoomimage());
                troRoom.setRoomnum(roomDTO.getRoomnum());
                troRoom.setRoomwifi(roomDTO.getRoomwifi());
                troRoom.setRoomprice(roomDTO.getRoomprice());
               int x= troRoomService.updateByPrimaryKeySelective(troRoom);   //更新

                Room room = new Room();
                room.setRoomname(roomDTO.getRoomintroduce());
                room.setRoomnumber(roomDTO.getRoomnumber());
                room.setRoomprice(roomDTO.getRoomprice());
               int y= roomService.updateByPrimaryKeySelective(room);   //更新
                if(x==0 && y==0)
                    return resultDTO.fail("更新失败");
                return resultDTO.ok(null);

            }
            return resultDTO.fail("接收数据为空");
        }catch(Exception e){
            return resultDTO.unkonwFail(e.toString());
        }



    }


    @RequestMapping("/api/back/deleteroom")  //删除房间
    public ResultDTO deleteRoom(String roomintroduce) {
        ResultDTO resultDTO = new ResultDTO();
        try {
            if(roomintroduce !=null && roomintroduce !="") {
                roomService.deleteByPrimaryKey(roomintroduce);
                troRoomService.deleteByPrimaryKey(roomintroduce);
                return resultDTO.ok(null);
            }
            return  resultDTO.fail("接收数据为空");
        }

        catch(Exception e)

        {
            return resultDTO.unkonwFail(e.toString());
        }
    }

}
