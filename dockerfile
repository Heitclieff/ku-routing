FROM oven/bun

WORKDIR /app

COPY package.json .
COPY bun.lockb .

RUN bun install

COPY src src
COPY tsconfig.json .
COPY prisma prisma

# COPY public public
RUN bunx prisma generate

ENV NODE_ENV production
CMD ["bun","src/index.ts"]
