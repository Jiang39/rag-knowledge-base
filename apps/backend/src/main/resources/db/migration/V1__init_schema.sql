-- 初始表结构（物理删除为默认策略）
CREATE TABLE IF NOT EXISTS document (
  id BIGSERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  source_type TEXT,
  source_path TEXT,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS chunk (
  id BIGSERIAL PRIMARY KEY,
  document_id BIGINT NOT NULL REFERENCES document(id) ON DELETE CASCADE,
  ordinal INT NOT NULL,
  text TEXT NOT NULL,
  tokens INT,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 向量表：使用 pgvector，维度以 384 为初始（可迁移调整）
CREATE TABLE IF NOT EXISTS chunk_embedding (
  id BIGSERIAL PRIMARY KEY,
  chunk_id BIGINT NOT NULL REFERENCES chunk(id) ON DELETE CASCADE,
  embedding VECTOR(384) NOT NULL,
  model TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 可选会话/消息（后续聊天）
CREATE TABLE IF NOT EXISTS conversation (
  id BIGSERIAL PRIMARY KEY,
  user_id TEXT,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS message (
  id BIGSERIAL PRIMARY KEY,
  conversation_id BIGINT NOT NULL REFERENCES conversation(id) ON DELETE CASCADE,
  role TEXT NOT NULL,
  content TEXT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

