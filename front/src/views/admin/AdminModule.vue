<template>
  <a-row style="height: 100%">
    <a-col :span="4" style="border: 1px solid #eee;height: 100%">
      <a-directory-tree
          style="padding-top: 10px;height: 100%;"
          multiple
          :fieldNames="{children:'children', title:'moduleName', key:'id' }"
          :tree-data="moduleList"
          v-model:expandedKeys="openKeys"
          @select="handClick"
      ></a-directory-tree>
    </a-col>
    <a-col :span="20">
      <a-space :size="10" style="margin: 10px 0px 0px 10px">
        <a-button type="primary" @click="add">添加</a-button>
        <a-button type="primary" @click="update">修改</a-button>
        <a-button type="primary" @click="handleDelete">删除</a-button>
      </a-space>
      <a-form
          style="padding-top: 10px"
          layout="horizontal"
          :model="module"
          name="basic"
          :label-col="{ span: 2 }"
          :wrapper-col="{ span: 8 }"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label="模块ID"
            name="id"
            v-show="false"
        >
          <a-input v-model:value="module.id"/>
        </a-form-item>
        <a-form-item
            label="模块PID"
            name="parentId"
            v-show="false"
        >
          <a-input v-model:value="module.parentId"/>
        </a-form-item>
        <a-form-item
            label="模块名称"
            name="moduleName"
            :rules="[{ required: true, message: '请输入模块名称' }]"
        >
          <a-input :disabled="disabled" v-model:value="module.moduleName"/>
        </a-form-item>
        <a-form-item
            label="模块URL"
            name="moduleUrl"
            :rules="[{ required: true, message: '请输入模块URL' }]"
        >
          <a-input :disabled="disabled" v-model:value="module.moduleUrl"/>
        </a-form-item>
        <a-form-item
            label="排序"
            name="moduleSort"
            :rules="[{validator: checkSort }]"
        >
          <a-input-number :disabled="disabled" v-model:value="module.moduleSort"></a-input-number>
        </a-form-item>
        <a-form-item :wrapper-col="{ span: 8, offset: 2 }">
          <a-button type="primary" html-type="submit" size="large" :loading="loading">确认</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, ref, watch} from 'vue';
import {message, TreeProps} from "ant-design-vue";
import {Rule} from "ant-design-vue/es/form";
import axios from "axios";
import {Tool} from "@/util/tool";

interface FormState {
  id: any;
  parentId: any;
  moduleName: string;
  moduleUrl: string;
  moduleSort: number;
}

export default defineComponent({
  name: "AdminModule",
  components: {},
  setup() {
    const modules = ref();
    modules.value = []

    const moduleList = ref();
    moduleList.value = []

    let openKeys = ref();
    openKeys.value = []

    const module = ref();
    module.value = reactive<FormState>({
      id: undefined,
      parentId: undefined,
      moduleName: '',
      moduleUrl: '',
      moduleSort: 0,
    });

    const disabled = ref(true)

    const onFinish = (values: any) => {
      console.log('Success:', values);
      loading.value = true
      axios.post('admin/module/insert', {
        ...values
      }).then(response => {
        const result = response.data
        if (result.code === 200) {
          message.success(`操作成功！`)
        } else {
          message.success(result.message)
        }
        setTimeout(function () {
          loading.value = false
          handleQueryModuleList()
        }, 200);
      })
    };

    const onFinishFailed = (errorInfo: any) => {
      message.error(`存在未填项！`)
    };

    const checkSort = (rule: Rule, value: number) => {
      if (!Number.isInteger(value)) {
        return Promise.reject('请输入数字！');
      } else {
        if (value > 999) {
          return Promise.reject('小于999');
        } else {
          return Promise.resolve();
        }
      }
    }

    const loading = ref(false);

    const handleQueryModuleList = () => {
      loading.value = true
      axios.post('admin/module/list').then(
          response => {
            const result = response.data
            if (result.code == 200) {
              modules.value = Tool.copy(result.data)
              moduleList.value = Tool.array2Tree(result.data, 0);
              console.log(moduleList.value)
              openKeys.value = result.data.map((item: any) => {
                return item.id
              })
              console.log(openKeys)
            } else {
              message.error(result.message);
            }

            setTimeout(function () {
              loading.value = false
            }, 200);
          }
      )
    }

    const handClick = (value: any) => {
      const id = value[0];
      module.value = modules.value.find((item: any) => {
        return item.id === id
      })
      disabled.value = true
      parentId.value = id
    }

    const update = () => {
      if (!parentId.value) {
        message.warn(`请您先选择一个节点`)
        return
      }
      handClick([parentId.value])
      disabled.value = false
    }

    const add = (value: any) => {
      console.log(value)
      if (!parentId.value) {
        message.warn(`请您先选择一个节点`)
        return
      }
      module.value = {
        "parentId": parentId.value
      }
      disabled.value = false
    }

    const parentId = ref()

    const handleDelete = () => {
      if (!parentId.value) {
        message.warn(`请您先选择一个节点`)
        return
      }
      const id = parentId.value
      let ids = Tool.getSubTreeIds(moduleList.value, id) || [].map((item: any) => {
        return item.id
      })
      ids.unshift(id)
      console.log(`删除的ids为${ids}`)
      loading.value = true
      axios.post(`admin/module/delete/${ids.join(',')}`).then(response => {
        const result = response.data
        if (result.code === 200) {
          message.success(`删除成功!`)
        } else {
          message.error(result.message)
        }
        setTimeout(function () {
          loading.value = false
          handleQueryModuleList()
        }, 200);

      })
    }

    onMounted(() => {
      handleQueryModuleList()
    })

    return {
      onFinish,
      onFinishFailed,
      checkSort,
      moduleList,
      handClick,
      module,
      openKeys,
      disabled,
      update,
      add,
      parentId,
      handleDelete,
      loading
    };
  },
});
</script>

<style scoped>

</style>