<template>
  <a-layout-footer style="text-align: center" class="footer">
      Ant Design ©2018 Created by Ant UED
  </a-layout-footer>
</template>

<script lang="ts">
import {defineComponent, computed, ref} from 'vue';


export default defineComponent({
  name: "the-header",
  components: {},
  setup() {

    return {};
  },
});
</script>

<style>
.footer {
  position: absolute;
  width: 100%;
  height: 40px;
  padding: 10px 0 !important;
  bottom: 0px;
  background-color: cornflowerblue !important;
  font-size: 18px;
  font-weight: bolder;
  color: #fff !important;
}
</style>