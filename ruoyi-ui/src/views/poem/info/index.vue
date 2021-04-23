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
      <el-form-item label="诗词朝代" prop="poemDynasty">
        <el-input
          v-model="queryParams.poemDynasty"
          placeholder="请输入诗词创作朝代"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="诗词作者" prop="poemWriterId">
        <el-input
          v-model="queryParams.poemWriterId"
          placeholder="请输入诗词作者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="诗词类别" prop="poemTypeId">
        <el-select v-model="queryParams.poemTypeId" placeholder="请选择诗词类别" clearable size="small">
          <el-option
            v-for="dict in poemTypeIdOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatus">
        <el-select v-model="queryParams.auditStatus" placeholder="请选择审核状态" clearable size="small">
          <el-option
            v-for="dict in auditStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['poem:info:add']"
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
          v-hasPermi="['poem:info:edit']"
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
          v-hasPermi="['poem:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['poem:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center" show-overflow-tooltip prop="id" width="70" />
      <el-table-column label="诗词名称" align="center" prop="poemName" />
      <el-table-column label="诗词创作朝代" align="center" show-overflow-tooltip prop="poemDynasty" />
      <el-table-column label="诗词作者" align="center" prop="poemWriterId" />
      <el-table-column label="诗词类别" align="center" prop="poemTypeId" :formatter="poemTypeIdFormat"/>
      <el-table-column label="创建用户" align="center" prop="userName" />
      <el-table-column label="诗词保存时间" align="center" prop="poemUploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.poemUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" :formatter="auditStatusFormat">
       <template slot-scope="scope">
        <el-tag v-if="scope.row.auditStatus==0" type="warning" >待上传</el-tag>
        <el-tag v-if="scope.row.auditStatus==1" >待审核</el-tag>
        <el-tag v-if="scope.row.auditStatus==2" type="danger">驳回</el-tag>
        <el-tag v-if="scope.row.auditStatus==3" type="success">已审核</el-tag>
      </template>
      </el-table-column>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-monitor"
            @click="handleSelect(scope.row)"
            v-hasPermi="['poem:info:query']"
          >查看</el-button>
          <el-button
            v-if="scope.row.auditStatus=='0'||scope.row.auditStatus=='2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['poem:info:edit']"
          >修改</el-button> 
          <el-button
            v-if="scope.row.auditStatus=='0'||scope.row.auditStatus=='2'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAudit(scope.row)"
            v-hasPermi="['poem:info:audit']"
          >上传</el-button>
          <el-button
            v-if="scope.row.auditStatus=='3'||scope.row.auditStatus=='1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUndo(scope.row)"
            v-hasPermi="['poem:info:undo']"
          >撤回</el-button>
          <el-button
            v-if="scope.row.auditStatus=='0'||scope.row.auditStatus=='2'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['poem:info:remove']"
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



    <!-- 新增、修改诗词信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-col :span="12">
        <el-form-item label="诗词名称" prop="poemName">
          <el-input v-model="form.poemName" placeholder="请输入诗词名称" />
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="诗词作者" prop="poemWriterId">
          <el-input v-model="form.poemWriterId" placeholder="请输入诗词作者" />
        </el-form-item>
        </el-col>
        
        <el-col :span="12">
        <el-form-item label="诗词类别" prop="poemTypeId">
          <el-select v-model="form.poemTypeId" placeholder="请选择诗词类别ID">
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
          <el-input v-model="form.poemDynasty" placeholder="请输入诗词创作朝代" />
        </el-form-item>
        </el-col>

        <el-col :span="24">
        <el-form-item label="诗词内容" prop="poemContent">
          <editor v-model="form.poemContent" :min-height="70"/>
          <!-- <el-input v-model="form.poemContent" type="textarea" placeholder="请输入诗词内容" show-word-limit
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
        </el-form-item>
        <el-form-item label="诗词注释" prop="poemAnnotation">
          <editor v-model="form.poemAnnotation" :min-height="70"/>
          <!-- <el-input v-model="form.poemAnnotation" type="textarea" placeholder="请输入诗词注释" show-word-limit
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
        </el-form-item>
        <el-form-item label="诗词赏析" prop="poemAppreciation">
          <editor v-model="form.poemAppreciation" :min-height="110"/>
          <!-- <el-input v-model="form.poemAppreciation" type="textarea" placeholder="请输入诗词注释" show-word-limit
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
          
        </el-form-item>
        <el-form-item label="诗词感悟" prop="poemSentiment">
          <editor v-model="form.poemSentiment" :min-height="110"/>
          <!-- <el-input v-model="form.poemSentiment" type="textarea" placeholder="请输入诗词注释" show-word-limit
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
        </el-form-item>
        <el-form-item label="图片">
          <imageUpload v-model="form.picture"/>
        </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">保 存</el-button>
        <el-button @click="cancel">取 消</el-button>
        
      </div>
    </el-dialog>
  </div>
</template>



<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo, exportInfo , auditInfo , undoInfo } from "@/api/poem/info";
import ImageUpload from '@/components/ImageUpload';
import Editor from '@/components/Editor';

export default {
  name: "Info",
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
      poemNames: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 诗词信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        poemName: null,
        poemDynasty: null,
        poemWriterId: null,
        poemContent: null,
        poemAnnotation: null,
        poemAppreciation: null,
        poemSentiment: null,
        picture: null,
        poemTypeId: null,
        userName: null,
        poemUploadTime: null,
        auditStatus: null,
        auditorId: null,
      },
      // 表单参数
      form: {
        poemName:'',
        poemWriterId:'',
        poemTypeId:'',
        poemDynasty:'',
        poemContent:'',

      },
      // 表单校验
      rules: {
        poemName:[{
          required: true,
          message: '请输入诗词名称',
          trigger: 'blur'
        }],
        poemWriterId:[{
          required: true,
          message: '请输入诗词作者',
          trigger: 'blur'
        }],
        poemTypeId:[{
          required: true,
          message: '请选择诗词类型',
          trigger: 'blur'
        }],
        poemDynasty:[{
          required: true,
          message: '请输入诗词朝代',
          trigger: 'blur'
        }],
        poemContent:[{
          required: true,
          message: '请输入诗词内容',
          trigger: 'blur'
        }],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("poem_type_list").then(response => {
      this.poemTypeIdOptions = response.data;
    });
    this.getDicts("poem_audit_status").then(response => {
      this.auditStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询诗词信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 诗词类别字典翻译
    poemTypeIdFormat(row, column) {
      return this.selectDictLabel(this.poemTypeIdOptions, row.poemTypeId);
    },
    // 审核状态字典翻译
    auditStatusFormat(row, column) {
      return this.selectDictLabel(this.auditStatusOptions, row.auditStatus);
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
        poemName: null,
        poemDynasty: null,
        poemWriterId: null,
        poemContent: null,
        poemAnnotation: null,
        poemAppreciation: null,
        poemSentiment: null,
        picture: null,
        poemTypeId: null,
        userName: null,
        poemUploadTime: null,
        auditStatus: 0,
        auditorId: null,
        delFlag: 0
      };
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
      this.poemNames = selection.map(item => item.poemName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加诗词信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改诗词信息";
      });
    },
    /** 上传按钮操作 */
    handleAudit(row) {
      const ids = row.id || this.ids;
      const poemNames = row.poemName || this.poemNames;
      this.$confirm('是否确认上传诗词名称为"' + poemNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return auditInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("上传成功");
        })
    },
    /** 撤回按钮操作 */
    handleUndo(row) {
      const id = row.id || this.ids;
      const poemNames = row.poemName || this.poemNames;
      this.$confirm('是否确认撤回诗词名称为"' + poemNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return undoInfo(id);
        }).then(() => {
          this.getList();
          this.msgSuccess("撤回成功");
        })
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看诗词信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      const poemNames = row.poemName || this.poemNames;
      this.$confirm('是否确认删除诗词名称为"' + poemNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有诗词信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
