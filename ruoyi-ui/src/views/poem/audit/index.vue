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
          <el-option label="待审核" value="1" />
          <el-option label="已审核" value="3" />
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['poem:audit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="auditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="index" label="序号" align="center" prop="id" width="70" />
      <el-table-column label="诗词名称" align="center" prop="poemName" />
      <el-table-column label="诗词创作朝代" align="center" prop="poemDynasty" />
      <el-table-column label="诗词作者" align="center" prop="poemWriterId" />
      <el-table-column label="诗词类别" align="center" prop="poemTypeId" :formatter="poemTypeIdFormat"/>
      <el-table-column label="上传用户" align="center" prop="userName" />
      <el-table-column label="诗词上传时间" align="center" prop="poemUploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.poemUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" :formatter="auditStatusFormat">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.auditStatus==1">待审核</el-tag>
          <el-tag v-if="scope.row.auditStatus==3" type="success">已审核</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="审核人" align="center" prop="auditorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-monitor"
            @click="handleSelect(scope.row)"
            v-hasPermi="['poem:audit:query']"
          >查看</el-button>
          <el-button
            v-if="scope.row.auditStatus=='1'"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAudit(scope.row)"
            v-hasPermi="['poem:audit:audit']"
          >通过</el-button>
          <el-button
            v-if="scope.row.auditStatus=='3' || scope.row.auditStatus=='1'"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleReaudit(scope.row)"
            v-hasPermi="['poem:audit:reject']"
          >驳回</el-button>
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

   <!-- 查看诗词信息对话框 -->
     <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-col :span="12">
        <el-form-item label="诗词名称" prop="poemName">
          <el-input v-model="form.poemName" readonly/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="诗词作者" prop="poemWriterId" >
          <el-input v-model="form.poemWriterId"  readonly />
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
          <el-input v-model="form.poemDynasty" readonly/>
        </el-form-item>
        </el-col>

        <el-col :span="24">
          <el-form-item label="诗词内容" prop="poemContent">
            <editor v-model="form.poemContent" :min-height="70"/>
          <!-- <el-input v-model="form.poemContent" type="textarea"  show-word-limit readonly
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
          </el-form-item>
        <el-form-item label="诗词注释" prop="poemAnnotation">
          <editor v-model="form.poemAnnotation" :min-height="70"/>
          <!-- <el-input v-model="form.poemAnnotation" type="textarea"  show-word-limit readonly
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
        </el-form-item>
        <el-form-item label="诗词赏析" prop="poemAppreciation">
          <editor v-model="form.poemAppreciation" :min-height="110"/>
          <!-- <el-input v-model="form.poemAppreciation" type="textarea"  show-word-limit readonly
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
          
        </el-form-item>
        <el-form-item label="诗词感悟" prop="poemSentiment">
          <editor v-model="form.poemSentiment" :min-height="110"/>
          <!-- <el-input v-model="form.poemSentiment" type="textarea" show-word-limit readonly
            :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input> -->
        </el-form-item>
        <el-form-item label="图片">
          <imageUpload v-model="form.picture" :disabled='true'/>
        </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="cancel">确 定</el-button>   
      </div>
    </el-dialog>
    
  </div>
</template>

<script>
import { listAudit, getAudit, audit , reaudit, addAudit, updateAudit, exportAudit } from "@/api/poem/audit";
import ImageUpload from '@/components/ImageUpload';
import Editor from '@/components/Editor';

export default {
  name: "Audit",
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
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 诗词审核表格数据
      auditList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 诗词类别字典
      poemTypeIdOptions: [],
      // 审核状态字典
      auditStatusOptions: [],
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
    this.getDicts("poem_audit_status").then(response => {
      this.auditStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询诗词审核列表 */
    getList() {
      this.loading = true;
      listAudit(this.queryParams).then(response => {
        this.auditList = response.rows;
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
        auditStatus: null,
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加诗词审核";
    },
    /** 查看按钮操作 */
    handleSelect(row) {
      this.reset();
      const id = row.id || this.ids
      getAudit(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看诗词";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAudit(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAudit(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 通过按钮操作 */
    handleAudit(row) {
      const ids = row.id || this.ids;
      const poemNames = row.poemName || this.poemNames;
      this.$confirm('是否确认审核通过上传诗词名称为"' + poemNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return audit(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("审核通过成功");
        })
    },
    /** 驳回按钮操作 */
    handleReaudit(row) {
      const ids = row.id || this.ids;
      const poemNames = row.poemName || this.poemNames;
      this.$confirm('是否确认驳回诗词名称为"' + poemNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return reaudit(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("驳回成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有诗词审核数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAudit(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
