<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="诗词ID" prop="poemId">
        <el-input
          v-model="queryParams.poemId"
          placeholder="请输入诗词ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
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
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['poem:share:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['poem:share:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['poem:share:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['poem:share:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="shareList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="诗词ID" align="center" prop="poemId" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="备注" align="center" prop="comment" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['poem:share:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['poem:share:remove']"
          >删除</el-button>
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

    <!-- 添加或修改诗词分享对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="诗词ID" prop="poemId">
          <el-input v-model="form.poemId" placeholder="请输入诗词ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="备注" prop="comment">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-divider content-position="center">诗词审核信息</el-divider>
        <el-table :data="poemInfoList" :row-class-name="rowPoemInfoIndex" @selection-change="handlePoemInfoSelectionChange" ref="poemInfo">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="诗词名称" prop="poemName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemName" placeholder="请输入诗词名称" />
            </template>
          </el-table-column>
          <el-table-column label="诗词创作朝代" prop="poemDynasty">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemDynasty" placeholder="请输入诗词创作朝代" />
            </template>
          </el-table-column>
          <el-table-column label="诗词作者" prop="poemWriterId">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemWriterId" placeholder="请输入诗词作者" />
            </template>
          </el-table-column>
          <el-table-column label="诗词内容" prop="poemContent">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemContent" placeholder="请输入诗词内容" />
            </template>
          </el-table-column>
          <el-table-column label="诗词注释" prop="poemAnnotation">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemAnnotation" placeholder="请输入诗词注释" />
            </template>
          </el-table-column>
          <el-table-column label="诗词赏析" prop="poemAppreciation">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemAppreciation" placeholder="请输入诗词赏析" />
            </template>
          </el-table-column>
          <el-table-column label="诗词感悟" prop="poemSentiment">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemSentiment" placeholder="请输入诗词感悟" />
            </template>
          </el-table-column>
          <el-table-column label="图片" prop="picture">
            <template slot-scope="scope">
              <el-input v-model="scope.row.picture" placeholder="请输入图片" />
            </template>
          </el-table-column>
          <el-table-column label="诗词类别" prop="poemTypeId">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemTypeId" placeholder="请输入诗词类别" />
            </template>
          </el-table-column>
          <el-table-column label="上传用户" prop="userName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userName" placeholder="请输入上传用户" />
            </template>
          </el-table-column>
          <el-table-column label="诗词上传时间" prop="poemUploadTime">
            <template slot-scope="scope">
              <el-input v-model="scope.row.poemUploadTime" placeholder="请输入诗词上传时间" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listShare, getShare, delShare, addShare, updateShare, exportShare } from "@/api/poem/share";

export default {
  name: "Share",
  components: {
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
      // 诗词分享表格数据
      shareList: [],
      // 诗词审核表格数据
      poemInfoList: [],
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
        comment: null
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
  },
  methods: {
    /** 查询诗词分享列表 */
    getList() {
      this.loading = true;
      listShare(this.queryParams).then(response => {
        this.shareList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        delFlag: null,
        comment: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加诗词分享";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getShare(id).then(response => {
        this.form = response.data;
        this.poemInfoList = response.data.poemInfoList;
        this.open = true;
        this.title = "修改诗词分享";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.poemInfoList = this.poemInfoList;
          if (this.form.id != null) {
            updateShare(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addShare(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除诗词分享编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delShare(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
	/** 诗词审核序号 */
    rowPoemInfoIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 诗词审核添加按钮操作 */
    handleAddPoemInfo() {
      let obj = {};
      obj.poemName = "";
      obj.poemDynasty = "";
      obj.poemWriterId = "";
      obj.poemContent = "";
      obj.poemAnnotation = "";
      obj.poemAppreciation = "";
      obj.poemSentiment = "";
      obj.picture = "";
      obj.poemTypeId = "";
      obj.userName = "";
      obj.poemUploadTime = "";
      obj.auditStatus = "";
      obj.auditorId = "";
      this.poemInfoList.push(obj);
    },
    /** 诗词审核删除按钮操作 */
    handleDeletePoemInfo() {
      if (this.checkedPoemInfo.length == 0) {
        this.$alert("请先选择要删除的诗词审核数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.poemInfoList.splice(this.checkedPoemInfo[0].index - 1, 1);
      }
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
      this.$confirm('是否确认导出所有诗词分享数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportShare(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
