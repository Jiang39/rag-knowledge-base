<script setup lang="ts">
</script>

<template>
  <div class="container">
    <el-result icon="success" title="Frontend OK" sub-title="Vue3 + TypeScript + Element Plus 已就绪" />
  </div>
  <div class="tip">如果未看到“Frontend OK”，请清空浏览器缓存并刷新。</div>
  <div class="hint">开发命令：pnpm run dev</div>
  <div class="hint">后端健康检查：/api/health（端口 8080）</div>
</template>

<style scoped>
.container { padding: 24px; }
.tip { color: #999; margin-top: 12px; }
.hint { color: #666; margin-top: 4px; }
</style>
