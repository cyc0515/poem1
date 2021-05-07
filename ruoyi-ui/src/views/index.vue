<template>
  <div class="app-container home">
    <!-- <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <hr />
      </el-col>
    </el-row> -->
    
      <!-- <el-col :sm="24" :lg="24" style="padding-left: 20px">
        <h2>欢迎登录古诗词后台管理系统。</h2>      
      </el-col> -->
      <!-- 走马灯 -->
      <el-carousel :interval="5000" arrow="always" height="450px">
        <el-carousel-item v-for="item in pictures" :key="item">
          <el-image :src="item" ></el-image>
        </el-carousel-item>
      </el-carousel>
<!-- <el-row :gutter="20">
    </el-row> -->
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>通知公告</span>
          </div>
          <el-table v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange" height="500">
            <el-table-column type="index" label="序号" align="center" prop="noticeId" width="70" />
            <el-table-column
              label="公告类型"
              align="center"
              prop="noticeType"
              :formatter="typeFormat"
              width="80"
            />
            <el-table-column label="公告标题"
              align="center"
              prop="noticeTitle"
              :show-overflow-tooltip="true"
            />           
            <el-table-column label="发布时间" align="center" prop="createTime" width="100">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
          </el-table>
              <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getList"
            />
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>
              日历
            </span>
          </div>
          
            <div class="body" >
                <div class="date-headers">
                <div class="date-header">
                    <div><el-button type="primary" @click="handlePrev"><i class="el-icon-arrow-left"></i>上一月</el-button></div>
                    <div>{{ year }}年{{ month }}月{{ day }}日</div>
                    <div><el-button type="primary" @click="handleNext">下一月<i class="el-icon-arrow-right"></i></el-button></div>
                    <!-- <div><el-button type="primary" icon="el-icon-refresh-left" @click="refresh"></el-button></div> -->
                </div>
                </div>
                <div class="date-content" >
                    <div class="week-header">
                        <div
                          v-for="item in ['日','一','二','三','四','五','六']"
                          :key=item
                        >{{ item }}
                        </div>
                    </div>
                    <div class="week-day">
                        <div
                          class="every-day"
                          v-for="item in 42"
                          :key="item"
                          @click="handleClickDay(item - beginDay)"
                        >
                            <div v-if="item - beginDay > 0 && item - beginDay <= curDays"     :class="`${year}-${month}-${item - beginDay}` === `${year}-${month}-${day}` ? 'nowDay':''"
                            >{{ item - beginDay }}</div>
                            <div class="other-day" v-else-if="item - beginDay <= 0">{{ item - beginDay + prevDays }}</div>
                            <div class="other-day" v-else>{{ item - beginDay -curDays }}</div>
                        </div>
                    </div>
                </div>
            </div>
            <el-divider />
            <div class="body" >
              <el-image :src="require(`./sd.png`)" > </el-image>
            </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>
              天气
            </span>
          </div>
          <div class="body">
  <div>

    <iframe scrolling="no" src="https://tianqiapi.com/api.php?style=tx&skin=grape&city=青岛" frameborder="0" width="100%" height="30" allowtransparency="true"></iframe>
  </div>
           
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>


<script>
import { listNotice2, getNotice, delNotice, addNotice, updateNotice, exportNotice } from "@/api/system/notice";
import Editor from '@/components/Editor';
import z1 from './z1.jpg';
import z2 from './z2.jpg';

export default {
  data() {
    return {
      // 版本号
      version: "1.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    
  },


  name: "Notice",
  components: {
    Editor
  },
  data() {
    return {
      value: new Date(),
      pictures: [z1,
        z2],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 公告表格数据
      noticeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型数据字典
      statusOptions: [],
      // 状态数据字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        noticeTitle: undefined,
        createBy: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        noticeTitle: [
          { required: true, message: "公告标题不能为空", trigger: "blur" }
        ],
        noticeType: [
          { required: true, message: "公告类型不能为空", trigger: "change" }
        ]
      },
      year: null,
      month: null,
      day: null,
      currentDay: null,
      currentYearMonthTimes: null,//当前年的月的天数
      monthOneDay: null,//一个月中的某一天
      curDate: null,
      prevDays: null,
    };
  },
  computed: {
    curDays() {
        return new Date(this.year, this.month, 0).getDate();
    },
    // 设置该月日期起始值（找到一号是在哪里）
    beginDay() {
        return new Date(this.year, this.month - 1, 1).getDay();
    },
  },
  created() {
    this.getList();
    this.getDicts("sys_notice_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("sys_notice_type").then(response => {
      this.typeOptions = response.data;
    });
     this.getInitDate();
            this.currentYearMonthTimes = this.mGetDate(this.year, this.month); //本月天数
            this.prevDays = this.mGetDate(this.year, this.month - 1);
            this.curDate = `${this.year}-${this.month}-${this.day}`
            console.log(this.curDate)
  },
  methods: {
    handleClickDay(day){  //点击这一天，绑定点击事件
        console.log( '形参传进来的',day)
        console.log( 'data里面的this.day',this.day)
        console.log( 'data里面的currentYearMonthTimes',this.currentYearMonthTimes)
        this.day = day
        if(this.day > this.currentYearMonthTimes){
            this.$message.warning('不能选择超出本月的日期');
        }
        console.log(day)
    },
    computedDay() {
        const allDay = new Date(this.year, this.month, 0).getDate();
        if (this.day > allDay) {
            this.day = allDay;
        }
    },
    //设置页头显示的年月日
    getInitDate() {
        const date = new Date();
        this.year = date.getFullYear();
        this.month = date.getUTCMonth() + 1;
        this.day = date.getDate();
    },
    // 如果要获取当前月份天数:
    mGetDate() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var d = new Date(year, month, 0);
        return d.getDate();
    },
    handlePrev() {
        if (this.month == 1) {
            this.month = 12
            this.year--
        } else {
            this.month--
        }
        this.computedDay()
    },
    handleNext() {
        if (this.month == 12) {
            this.month = 1
            this.year++
        } else {
            this.month++
        }
        this.computedDay()
    },
    /** 查询公告列表 */
    getList() {
      this.loading = true;
      listNotice2(this.queryParams).then(response => {
        this.noticeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 公告状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 公告状态字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.noticeType);
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    }
   
  }
};
</script>

<style scoped>
    * {
        /* margin: 0px; */
        border: 0px;
        /* list-style: none; */
    }

    .calender2 {
        border-radius: 4px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        height: 456px;
        width: 485px;
        border: 1px solid #ccc;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
    }

    .date-header {
        height: 60px;
        width: 485px;
        display: flex;
        align-items: center;
        justify-content: space-around;
    }
    .date-headers {
        height: 60px;
        width: 485px;
        display: flex;
    }

    .pre-month {
        position: absolute;
        display: inline-block;
        height: 0px;
        width: 0px;
        border: 15px solid;
        border-color: transparent rgb(35, 137, 206) transparent transparent;
    }

    .next-month {
        position: absolute;
        display: inline-block;
        height: 0px;
        width: 0px;
        border: 15px solid;
        border-color: transparent transparent transparent rgb(35, 137, 206);
    }

    .show-date {
        margin-left: 40px;
        margin-top: 0px;
        display: inline-block;
        line-height: 30px;
        text-align: center;
        width: 310px;
        color: rgb(35, 137, 206);
    }

    .week-header {
        color: #000000;
        font-size: 14px;
        text-align: center;
        line-height: 30px;
    }

    .week-header div {
        margin: 0;
        padding: 0;
        display: inline-block;
        text-align: center;
        height: 20px;
        width: 67px;
    }

    .every-day {
        display: inline-block;
        margin-left: 1px;
        height: 50px;
        width: 67px;
        text-align: center;
        line-height: 50px;
    }

    .other-day {
        color: #ccc;
    }

    .nowDay {
        background: rgb(121, 35, 206);
        border: 1px solid #87c66d;
    }

    .active-day {
        /* padding: 2px */
        /* border-sizing:content-box; */
        border: 2px solid rgb(35, 137, 206);
    }
</style>


<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

