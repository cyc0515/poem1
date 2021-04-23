<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="诗词名称" prop="poemName">
        <el-input
          v-model="queryParams.poemName"
          placeholder="请输入诗词名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['poem:collect:remove']"
        >取消收藏</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="collectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center" prop="id" width="70" />
      <el-table-column label="诗词名称" align="center" prop="poemName" />
      <el-table-column label="诗词朝代" align="center" prop="poemDynasty" />
      <el-table-column label="诗词作者" align="center" prop="poemWriterId" />
      <el-table-column label="发表用户" align="center" prop="userName" />
      <el-table-column label="备注" align="center" prop="comment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-monitor"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['poem:collect:query']"
          >查看</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['poem:collect:remove']"
          >取消收藏</el-button>
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

    <!-- 查看诗词收藏对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-col :span="12">
        <el-form-item label="诗词名称" prop="poemName">
            <el-input v-model="form.poemName" readonly />
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="诗词作者" prop="poemWriterId">
            <el-input v-model="form.poemWriterId" readonly />
        </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="诗词类别" prop="poemTypeId">
            <el-select v-model="form.poemTypeId" readonly>
              <el-option
                v-for="dict in poemTypeIdOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="诗词朝代" prop="poemDynasty">
            <el-input v-model="form.poemDynasty" readonly />
        </el-form-item>
        </el-col>
      <el-col :span="24">
        <el-form-item label="诗词内容" prop="poemContent">
            <!-- <el-input v-model="form.poemContent" placeholder="请输入诗词内容" /> -->
             <editor v-model="form.poemContent" :min-height="70"/>
        </el-form-item>

        <el-form-item label="诗词注释" prop="poemAnnotation">
          <editor v-model="form.poemAnnotation" :min-height="70"/>
            <!-- <el-input v-model="form.poemAnnotation" placeholder="请输入诗词注释" /> -->
        </el-form-item>

        <el-form-item label="诗词赏析" prop="poemAppreciation">
          <editor v-model="form.poemAppreciation" :min-height="110"/>
            <!-- <el-input v-model="form.poemAppreciation" placeholder="请输入诗词赏析" /> -->
        </el-form-item>
        

        <el-form-item label="诗词感悟" prop="poemSentiment">
          <editor v-model="form.poemSentiment" :min-height="110"/>
            <!-- <el-input v-model="form.poemSentiment" placeholder="请输入诗词感悟" /> -->
        </el-form-item>
        </el-col>

        
        
        <el-col :span="12">
        <el-form-item label="上传用户" prop="userName">
            <el-input v-model="form.userName" readonly/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="诗词上传时间"  label-width="120px">
          <el-input v-model="form.poemUploadTime" readonly/>
        </el-form-item>
        </el-col>

        <el-col :span="6">
          <el-form-item label="图片" prop="picture">
          <imageUpload v-model="form.picture" :disabled='true'/>
        </el-form-item>
        </el-col>

        <el-col :span="18">
        <el-form-item label="备注" prop="comment">
          <el-input v-model="form.comment" type="textarea"  show-word-limit readonly
            :autosize="{minRows: 4, maxRows: 7}" :style="{width: '100%'}"></el-input>
          <!-- <el-input v-model="form.comment" type="textarea"  readonly /> -->
        </el-form-item>
        </el-col>


      </el-form>  
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCollect, getCollect, delCollect, exportCollect } from "@/api/poem/collect";
import Editor from '@/components/Editor';
import ImageUpload from '@/components/ImageUpload';

export default {
  name: "Collect",
  components: {
     ImageUpload,
     Editor,
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedPoemInfo: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 诗词收藏表格数据
      collectList: [],
      // 诗词审核表格数据
      poemInfoList: [],
      // 诗词类别字典
      poemTypeIdOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        poemId: null,
        userId: null,
        comment: null,
        poemName: null,
        poemWriterId: null,
        userName: null,
        poemContent: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("poem_type_list").then(response => {
      this.poemTypeIdOptions = response.data;
    });
  },
  methods: {
    /** 查询诗词收藏列表 */
    getList() {
      this.loading = true;
      listCollect(this.queryParams).then(response => {
        this.collectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 诗词类别字典翻译
    poemTypeIdFormat(row, column) {
      return this.selectDictLabel(this.poemTypeIdOptions, row.poemTypeId);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        poemId: null,
        userId: null,
        delFlag: 0,
        poemName: null,
        poemWriterId: null,
        userName: null,
        comment: null,
        poemContent: null,
      };
      this.poemInfoList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCollect(id).then(response => {
        this.form = response.data;
        this.poemInfoList = response.data.poemInfoList;
        this.open = true;
        this.title = "查看诗词";
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认取消收藏?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCollect(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("取消收藏成功");
        })
    },
    /** 单选框选中数据 */
    handlePoemInfoSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.poemInfo.clearSelection();
        this.$refs.poemInfo.toggleRowSelection(selection.pop());
      } else {
        this.checkedPoemInfo = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有诗词收藏数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCollect(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
