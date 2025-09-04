<script setup lang="ts">
import { ref } from 'vue'

const form = ref({
  text: '这是一个RAG演示。我们按标点切分，然后做滑动窗口重叠。',
  window: 20,
  overlap: 5
})

const result = ref('')

async function onSplit() {
  result.value = ''
  const res = await fetch('http://localhost:8080/api/ingest/split', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      text: form.value.text,
      method: 'punctuation+window',
      window_size: form.value.window,
      overlap: form.value.overlap
    })
  })
  result.value = await res.text()
}
</script>

<template>
  <div class="container">
    <el-result icon="success" title="Frontend OK" sub-title="Vue3 + TypeScript + Element Plus 已就绪" />
    <div style="margin:16px 0;">
      <el-form :inline="true" :model="form" @submit.prevent>
        <el-form-item label="待分块文本">
          <el-input v-model="form.text" type="textarea" :rows="3" style="width:600px" placeholder="输入要分块的文本" />
        </el-form-item>
        <el-form-item label="窗口/重叠">
          <el-input-number v-model="form.window" :min="10" :max="500" />
          <el-input-number v-model="form.overlap" :min="0" :max="200" style="margin-left:8px;" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSplit">调用 /api/ingest/split</el-button>
        </el-form-item>
      </el-form>
      <el-card v-if="result" style="margin-top:12px;">
        <pre style="white-space:pre-wrap">{{ result }}</pre>
      </el-card>
    </div>
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
