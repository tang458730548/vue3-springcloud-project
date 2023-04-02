<template>
  <a-row style="height: 100%">
    <a-col :span="4" style="border: 1px solid #eee;height: 100%">
      <a-directory-tree
          style="padding-top: 10px;height: 100%"
          v-model:expandedKeys="expandedKeys"
          v-model:selectedKeys="selectedKeys"
          multiple
          :tree-data="treeData"
      ></a-directory-tree>
    </a-col>
    <a-col :span="20">
      <a-form
          style="padding-top: 10px"
          layout="horizontal"
          :model="formState"
          name="basic"
          :label-col="{ span: 2 }"
          :wrapper-col="{ span: 8 }"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label="模块名称"
            name="moduleName"
            :rules="[{ required: true, message: '请输入模块名称' }]"
        >
          <a-input v-model:value="formState.moduleName"/>
        </a-form-item>
        <a-form-item
            label="模块URL"
            name="moduleUrl"
            :rules="[{ required: true, message: '请输入模块URL' }]"
        >
          <a-input v-model:value="formState.moduleUrl"/>
        </a-form-item>
        <a-form-item
            label="排序"
            name="moduleSort"
            :rules="[{validator: checkSort }]"
        >
          <a-input-number v-model:value="formState.moduleSort"></a-input-number>
        </a-form-item>
        <a-form-item :wrapper-col="{ span: 8, offset: 2 }">
          <a-button type="primary" html-type="submit">确认</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>

<script lang="ts">
import {defineComponent, reactive, ref, watch} from 'vue';
import {message, TreeProps} from "ant-design-vue";
import {Rule} from "ant-design-vue/es/form";

interface FormState {
  moduleName: string;
  moduleUrl: string;
  moduleSort: number;
}

export default defineComponent({
  name: "AdminModule",
  components: {},
  setup() {
    const expandedKeys = ref<string[]>(['0-0', '0-1']);
    const selectedKeys = ref<string[]>([]);
    const treeData: TreeProps['treeData'] = [
      {
        title: 'parent 0',
        key: '0-0',
        children: [
          {
            title: 'leaf 0-0',
            key: '0-0-0',
            isLeaf: true,
          },
          {
            title: 'leaf 0-1',
            key: '0-0-1',
            isLeaf: true,
          },
        ],
      },
      {
        title: 'parent 1',
        key: '0-1',
        children: [
          {
            title: 'leaf 1-0',
            key: '0-1-0',
            isLeaf: true,
          },
          {
            title: 'leaf 1-1',
            key: '0-1-1',
            isLeaf: true,
          },
        ],
      },
    ];
    const formState = reactive<FormState>({
      moduleName: '',
      moduleUrl: '',
      moduleSort: 0,
    });
    const onFinish = (values: any) => {
      console.log('Success:', values);
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

    return {
      expandedKeys,
      selectedKeys,
      treeData,
      formState,
      onFinish,
      onFinishFailed,
      checkSort
    };
  },
});
</script>

<style scoped>

</style>