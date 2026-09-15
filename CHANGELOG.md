# Changelog

## [0.4.5](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.4.4..v0.4.5) - 2026-09-15

### 🚜 Refactor

- *(build)* Use version catalog for JVM toolchain and target configuration - ([6ef70e0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/6ef70e0878ef62191c9f1a5e9fc6957765e9ab8a))

### Action

- Update version in `gradle.properties` - ([d17b6e0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/d17b6e0155c76134324822b7ef9798b457dbee4e))


## [0.4.4](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.4.3..v0.4.4) - 2026-09-15

### 🚜 Refactor

- *(build)* Use version catalog for JVM toolchain and target configuration - ([18286ab](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/18286ab0d91427507b0f1d1bc54d3fa5c5c817f8))

### Action

- Update version in `gradle.properties` - ([b58ee74](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/b58ee74c24e2632dc67910be1c6ec73f55a7dce0))

### Build

- *(gradle)* Migrate dependency and plugin management to version catalog (libs.versions.toml) - ([8612674](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/86126749ca60c71a5fffc0470f3f3181ac9a45ad))


## [0.4.3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.4.2..v0.4.3) - 2026-09-14

### Action

- Update version in `gradle.properties` - ([5d096c9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/5d096c9e5b04a9e8b0a0a23a2d49b788904b2f09))

### Build

- Target JVM 17 bytecode while compiling with JDK 25 toolchain - ([c3d65fe](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c3d65fe8b1f81609bab952f25d0bf641fe9c14af))


## [0.4.2](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.4.1..v0.4.2) - 2026-09-14

### 🚜 Refactor

- *(EventBus)* Remove nullable type from `retList` to simplify usage - ([8d094b2](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/8d094b2faf48ba920ea49d93da374bb6fdcd48ce))

### 📚 Documentation

- *(README)* Update repository URL and dependency coordinates to reflect new namespace and hosting location - ([bd8ce77](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/bd8ce779857180de4219bce83c2d6c3490e8bc82))

### Action

- Update version in `gradle.properties` - ([1207252](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/12072528a49d9a59e97533c25ee35f8f93b57d43))


## [0.4.1](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.4.0..v0.4.1) - 2026-09-14

### 🐛 Bug Fixes

- Add Maven publication configuration to `publishing` block - ([051c621](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/051c6219881e27ee8d6d030231dfbd173d69f5de))

### 📚 Documentation

- *(README)* Add missing empty lines for code block clarity - ([967c087](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/967c087702476d83649e3996a875c4d519035517))

### Action

- Update version in `gradle.properties` - ([5ee0b8b](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/5ee0b8b5a9e94d80729680089bb5e2e41da9ccd8))


## [0.4.0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.21..v0.4.0) - 2026-09-14

### ⛰️  Features

- Update package structure to use `io.github.ykysnk` namespace - ([84e7bba](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/84e7bba20b830228704778ab98679490e5df2505))

### 🐛 Bug Fixes

- *(workflows)* Refactor release workflows and clean up unused file - ([5971bef](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/5971beff79cbba47d4f40e26e98b9285ee4ca273))
- *(workflows)* Enhance Dependabot auto-merge workflow - ([2b63695](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/2b636959ce464f9ce359a1112f519548778f6dbb))

### 🚜 Refactor

- *(EventBus)* Update `functionCheck` to return `Boolean` instead of throwing exception - ([327fa12](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/327fa1219ec46d353a25800bb93f01731b924880))
- *(EventBus)* Add Java interop methods and improve duration consistency - ([f947504](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/f94750472985a33df4783e15af106ef86d081ba0))
- *(EventBus)* Add overload for `publishUnSafe` with `timeout` as `Long` - ([949b58b](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/949b58bed3820db525d09752bd75b5b8308cf607))
- *(EventBus)* Replace `timeoutMillis` with `Duration` for improved readability and consistency - ([ad88914](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/ad88914d2aeeac77fa0da969694d1885e695a06c))
- *(EventBus, scopes)* Tidy imports and reformat code for improved readability - ([9521268](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/9521268c7638ec18b154a5e98ea07502eee10079))
- *(extensions)* Rename package for `ClassExtensions` file - ([bc39ce0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/bc39ce0c4463beda5a63444750690fa6202ec81e))
- *(scopes)* Replace `Job` with `SupervisorJob` for improved coroutine supervision and simplify scope launching by removing redundant `SupervisorJob` usage - ([2ccfadf](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/2ccfadfc8c49feb04aa8ad7a944e9a6e7b3efc1c))
- *(tests)* Update `EventBus` usage to leverage `Duration` for timeout handling - ([4d21f5c](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/4d21f5c0ae9696634db1a6a374f9d6ee58988975))
- *(tests)* Use `Duration` extensions for improved readability in delays - ([d0105c0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/d0105c0d84ceebda4795beae59197582fd5ffc1d))
- Optimize event bus concurrency, dispatch performance, and lifecycle management - ([ec242df](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/ec242df5c52104c864b14f9a934c632e99c32dfd))

### 🧪 Testing

- *(TestClass)* Add unit test placeholder method for `TestClass` - ([9cc0615](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/9cc0615a0006a2237005f42f4f0c2b603e551b05))

### Action

- Update version in `gradle.properties` - ([04179d9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/04179d9abb7f31f95689eb6ac6ccf1a7666bd173))

### Build

- *(gradle)* Update build scripts for dynamic configuration and publishing support - ([0ffed29](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/0ffed2947cecc5dd034b4abf9db8c05fa0dcc0db))

## New Contributors ❤️

* @junie-agent made their first contribution

## [0.3.21](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.20..v0.3.21) - 2026-09-14

### Build

- *(deps)* Bump jvm from 2.4.10 to 2.4.20 - ([c8f55e9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c8f55e9640b5e2db94b7f6b09ee16f673cc348c5))


## [0.3.20](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.19..v0.3.20) - 2026-09-14

### Action

- Update version in `gradle.properties` - ([fb32217](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/fb322178d343d236dc591b2d98c9013909515925))

### Build

- *(deps)* Bump gradle-wrapper from 9.6.1 to 9.7.1 - ([5dfbcf4](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/5dfbcf40575ec35b0fa47f5d02e5e6bda009cfea))


## [0.3.19](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.18..v0.3.19) - 2026-07-14

### Action

- Update version in `gradle.properties` - ([0889dd0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/0889dd0d7b06838174b0bf26ed731613cf1d19c7))

### Build

- *(deps)* Bump jvm from 2.4.0 to 2.4.10 - ([984e4c3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/984e4c3aeebffada052cb84d415a923aec328752))


## [0.3.18](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.17..v0.3.18) - 2026-06-27

### Action

- Update version in `gradle.properties` - ([645d755](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/645d7559b131423b0e3ec051ed72e0c4c5302ef9))

### Build

- *(deps)* Bump gradle-wrapper from 9.6.0 to 9.6.1 - ([8c66958](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/8c66958544534d74bd1c5924deecd7e6218044c4))


## [0.3.17](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.16..v0.3.17) - 2026-06-19

### Build

- *(deps)* Bump gradle-wrapper from 9.5.1 to 9.6.0 - ([5a76d08](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/5a76d08ee92ba3c2204ae863fe1e5a513e7da5e4))


## [0.3.16](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.15..v0.3.16) - 2026-06-08

### Action

- Update version in `gradle.properties` - ([d965e17](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/d965e17a3522533e4e6d93494fa3943a1dd08ddc))

### Build

- *(deps)* Bump jvm from 2.3.21 to 2.4.0 - ([2193cfd](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/2193cfd985a8c123d24071dfb308ed2101963c24))


## [0.3.15](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.14..v0.3.15) - 2026-05-14

### Action

- Update version in `gradle.properties` - ([7f2e7bf](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/7f2e7bf3ddc4e5c2ae4ac22ff6e442a8b9435d45))

### Build

- *(deps)* Bump gradle-wrapper from 9.5.0 to 9.5.1 - ([e466f09](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/e466f092485839e9a3b1fb461bf7e8c376cab300))


## [0.3.14](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.13..v0.3.14) - 2026-04-28

### Action

- Update version in `gradle.properties` - ([52defc3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/52defc317aca15a939fb526841498671a0cfc63e))

### Build

- *(deps)* Bump gradle-wrapper from 9.4.1 to 9.5.0 - ([7706257](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/770625731fc9fc35aa724d4cd17bda49c2807438))


## [0.3.13](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.12..v0.3.13) - 2026-04-26

### Build

- *(deps)* Bump jvm from 2.3.10 to 2.3.21 - ([14a426e](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/14a426edf9c70d56630f72944ecebf2621b2aa58))


## [0.3.12](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.11..v0.3.12) - 2026-04-26

### Action

- Update version in `gradle.properties` - ([f0d6b6e](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/f0d6b6e0f5040d13d186fcd8e054ae67a061ab2f))

### Build

- *(deps)* Bump gradle-wrapper from 9.4.0 to 9.4.1 - ([c9c795a](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c9c795a67bbd602023a34203420aeee585f7f538))


## [0.3.11](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.10..v0.3.11) - 2026-03-04

### Action

- Update version in `gradle.properties` - ([0c26ddd](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/0c26ddde80a4a1b62a35de63c5cd0876d7372a46))

### Build

- *(deps)* Bump gradle-wrapper from 9.3.1 to 9.4.0 - ([e2fe7a3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/e2fe7a39b339e6a21abbca71217503192219f414))


## [0.3.10](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.9..v0.3.10) - 2026-02-05

### Action

- Update version in `gradle.properties` - ([db709f1](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/db709f14a905e8844b7f537a41240e9e6d37b54b))

### Build

- *(deps)* Bump jvm from 2.3.0 to 2.3.10 - ([171ba52](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/171ba522a01a45f6e1fd08d04e33590640511e42))


## [0.3.9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.8..v0.3.9) - 2026-01-31

### Action

- Update version in `gradle.properties` - ([c5bf7df](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c5bf7dfb0829da11cb9b1b7bd543b911e527a318))

### Build

- *(deps)* Bump gradle-wrapper from 9.3.0 to 9.3.1 - ([1d35cb3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/1d35cb327b466f62a11c648554b6652fe0194a3d))


## [0.3.8](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.7..v0.3.8) - 2026-01-26

### Action

- Update version in `gradle.properties` - ([2219412](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/2219412823f27f4520426fa21c9791772b5f3646))

### Build

- *(deps)* Bump gradle-wrapper from 8.14.1 to 9.3.0 - ([4c79d1c](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/4c79d1cf7d5a21630f3082e0cd98c09555829d28))


## [0.3.7](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.6..v0.3.7) - 2025-12-16

### Action

- Update version in `gradle.properties` - ([fc364b6](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/fc364b69ce9aff14b81056beb05dd0e59e856fa3))

### Build

- *(deps)* Bump jvm from 2.2.21 to 2.3.0 - ([b19a2ae](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/b19a2aecb3e64d2ba1f46d0aa8cb11aeac30fcfd))


## [0.3.6](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.5..v0.3.6) - 2025-10-23

### 🐛 Bug Fixes

- *(workflows)* Add Dependabot auto-merge and enhance CI/CD workflows - ([2455893](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/2455893896bf8b64c50ad47efddd963e56c81752))

### Action

- Update version in `gradle.properties` - ([ffa5370](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/ffa5370d2eb615731886b7f0e7ac7889066f4a1d))


## [0.3.5](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.4..v0.3.5) - 2025-10-23

### Action

- Update version in `gradle.properties` - ([9152f21](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/9152f215295fe67b085ecbef1562727c55b8934b))

### Build

- *(deps)* Bump jvm from 2.2.0 to 2.2.21 - ([3ab282b](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/3ab282bf01d5be4d33dd0a17725cd0e182211747))


## [0.3.4](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.3..v0.3.4) - 2025-06-25

### 🐛 Bug Fixes

- *(LICENSE)* Add `LICENSE` - ([f6e8ed4](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/f6e8ed4b3eb37b5bf2959db451d7a259e6d45ec7))


## [0.3.3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.2..v0.3.3) - 2025-06-25

### Action

- Update version in `gradle.properties` - ([df592ed](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/df592ed88b0f3a9d02aa97e1fdff3bc15fb7b55a))

### Build

- *(deps)* Bump jvm from 2.1.21 to 2.2.0 - ([69b0121](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/69b012182d99acfa68d2b1da40fe1ecad9d625c9))


## [0.3.2](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.1..v0.3.2) - 2025-06-20

### 🐛 Bug Fixes

- *(README)* Update `README.md` - ([40557f9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/40557f9c1fe27dae07e58bf3be371164ab3df0a6))

### Action

- Update version in `gradle.properties` - ([15b2af8](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/15b2af82ad5fbb5b2a616403cfe9e955cc44674c))


## [0.3.1](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.3.0..v0.3.1) - 2025-06-20

### 🐛 Bug Fixes

- *(EventBus)* Formatted - ([1e4ba5c](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/1e4ba5cc4f3766b023ea2e1d73c0bc23f3ebd563))
- Fix event return handler - ([ba6cd02](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/ba6cd02919c71f254e73a85cbb5ed36982a93e9c))

### Action

- Update version in `gradle.properties` - ([08e27eb](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/08e27eb5e0934edf6640828dd11b4949b5202666))


## [0.3.0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.2.2..v0.3.0) - 2025-06-20

### ⛰️  Features

- Remove event value, add java support (Only `register`) - ([dfc221c](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/dfc221ca3950b7e24b4d7494f91ee323bb4e2fac))

### Action

- Update version in `gradle.properties` - ([01e12a6](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/01e12a6831efd217d4ed33472ca5ac2dcbe37184))


## [0.2.2](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.2.1..v0.2.2) - 2025-06-19

### 🐛 Bug Fixes

- *(EventBus)* Update todo - ([a13ae0b](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/a13ae0bd9431c3700cf0369ece2015677cce35fb))
- *(EventReturn)* Make `EventReturn` more like map - ([6b4cadb](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/6b4cadbec79c3e533d47b6fd6490086dc1e46a0e))
- Remove unused code, add more sample - ([95d639e](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/95d639e3c3aaf5f4a3dcf40a3546676c49d688fd))

### Action

- Update version in `gradle.properties` - ([053b09e](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/053b09e67a0bac0d9425ac8b5c26a43548a7d743))


## [0.2.1](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.2.0..v0.2.1) - 2025-06-18

### 🐛 Bug Fixes

- *(Event)* Update sample - ([b297490](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/b2974901f764fd28c09797f778805fe5025a7b75))
- *(EventBus)* Update todo - ([a95763a](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/a95763a6643062b5b3fb4e8e3a5c0583749ba854))

### Action

- Update version in `gradle.properties` - ([9c6fd1a](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/9c6fd1ae2f32e03ceb2ee4c410c50ad6f8da14ee))


## [0.2.0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.1.0..v0.2.0) - 2025-06-18

### ⛰️  Features

- Remove `returnType` form `EventReturn`, rename `EventReturn` to `EventReturnData` and add new class `EventReturn` - ([84f8953](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/84f8953dbf1ea7aa202040d40354240c75aa748c))

### Action

- Update version in `gradle.properties` - ([50beada](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/50beada8c1b7985f6b0be9b81519fbadcca5ac0c))


## [0.1.0](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.13..v0.1.0) - 2025-06-18

### ⛰️  Features

- Add return handle - ([adb665a](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/adb665a7aa68a3ba96d7a7100aa87a0793b3ffff))

### 🐛 Bug Fixes

- *(README)* Update `README.md` - ([18a7a8b](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/18a7a8b5ccd31dc8959211b21e033b94d9f77e89))

### Action

- Update version in `gradle.properties` - ([c7a3e53](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c7a3e534892fefe92a12cc65c47e151d611d7aaf))


## [0.0.13](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.12..v0.0.13) - 2025-06-18

### 🐛 Bug Fixes

- Add a timeout handle and docs - ([2d6e27c](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/2d6e27cd47a427e32733dfe2f20b6b714e29d690))
- Remove unused code - ([c332916](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c332916ff7cfb0311fff20876a332f5da4b0f7b8))

### Action

- Update version in `gradle.properties` - ([d161297](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/d161297d0ed86923ab5c432d269d60018a618f67))


## [0.0.12](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.11..v0.0.12) - 2025-06-15

### 🐛 Bug Fixes

- *(README)* Change `MutableSharedFlow` to `SharedFlow` - ([02f1dc3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/02f1dc3620a5fb4cbcc098792d3acb803dfaa26c))

### Action

- Update version in `gradle.properties` - ([d9b9380](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/d9b93803a6de74bce501b9352cfc98de5aa466bc))


## [0.0.11](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.10..v0.0.11) - 2025-06-15

### 🐛 Bug Fixes

- *(README)* Fix version in `README.md` again again - ([ea096c6](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/ea096c66b8aef25c8b43f4a049f10fda38923143))

### Action

- Update version in `gradle.properties` - ([c2fdf37](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c2fdf371a8da7dce575d976c03704e870f681d42))


## [0.0.10](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.9..v0.0.10) - 2025-06-15

### 🐛 Bug Fixes

- *(README)* Fix version in `README.md` again - ([661c0db](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/661c0db30c94be234ce6d86443798138156fe662))

### Action

- Update version in `gradle.properties` - ([ed99a25](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/ed99a25417866f8ca5fb239c41af3e53259c84e8))


## [0.0.9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.8..v0.0.9) - 2025-06-15

### 🐛 Bug Fixes

- *(README)* Fix version in `README.md` - ([d206302](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/d206302a6dc60ff8b311bad8df30b1f264959544))

### Action

- Update version in `gradle.properties` - ([1a01418](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/1a01418267ad9f2083e82939c208532e9fa9a541))


## [0.0.8](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.7..v0.0.8) - 2025-06-15

### 🐛 Bug Fixes

- *(workflow)* Fix version - ([c7a301e](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c7a301e867b757a3ad8bdf9db05fcf92b7e4febe))

### Action

- Update version in `gradle.properties` - ([b8e2e9f](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/b8e2e9f613764c23b0868dd55a257404cfc0fad8))


## [0.0.7](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.6..v0.0.7) - 2025-06-15

### 🐛 Bug Fixes

- *(workflow)* Update workflow stuffs - ([36ae7ab](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/36ae7abcf2142ac9ffcbc37bda9f872bbda624d8))
- Change `Event` class to abstract class - ([f6562d8](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/f6562d8aae07338f1894cd8afe5ede69d403f653))

### Action

- Update version in `gradle.properties` - ([dd26680](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/dd26680a5d1812c236b385c1deb33a9d8828fb07))


## [0.0.6](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.5..v0.0.6) - 2025-06-15

### Build

- *(deps)* Bump jvm from 2.1.20 to 2.1.21 - ([1d06de9](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/1d06de9d99e5e9118e20844049c2f184c1c1b668))


## [0.0.5](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.4..v0.0.5) - 2025-06-15

### Action

- Update version in `gradle.properties` - ([c1ea578](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/c1ea578353c05d2c083f893cb491addf1ac68a54))

### Build

- *(deps)* Bump org.gradle.toolchains.foojay-resolver-convention - ([1b20d44](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/1b20d44a2dd95b414fc9cca7933315644e1b0f0e))


## [0.0.4](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.3..v0.0.4) - 2025-06-15

### 🐛 Bug Fixes

- Rename package name - ([8890821](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/8890821be6c301ca3b64532a41a6f953d553bd60))
- Remove useless code - ([76ad33d](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/76ad33d6833b732c8d93620a3e808d485abf06ce))

### Action

- Update version in `gradle.properties` - ([e449daf](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/e449dafadf126a554fafb030b0692fc0fbe30167))


## [0.0.3](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.2..v0.0.3) - 2025-06-15

### 🐛 Bug Fixes

- *(Gradle)* Fix version of `kotlinx-coroutines` - ([0940b38](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/0940b3869f66c4a28a49b6985b7f57e5bd1b31a6))

### Action

- Update version in `gradle.properties` - ([341125a](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/341125adf2f7ee860cda5dc636fbf2294f61c9fe))


## [0.0.2](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/compare/v0.0.1..v0.0.2) - 2025-06-15

### 🐛 Bug Fixes

- *(Utils)* Change `Utils` to internal class - ([7164af6](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/7164af679fc394209e96ea444e064e88c2e34148))

### Action

- Update version in `gradle.properties` - ([8942617](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/89426179c2e22c8002a9d55ae7e60b53c9ef6acc))

## New Contributors ❤️

* @github-actions[bot] made their first contribution

## [0.0.1] - 2025-06-15

### 🐛 Bug Fixes

- Setup workflows - ([17eee51](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/17eee516da40851347fcebf82d24560b645d8f21))
- Remove `.idea` files - ([7b05a84](https://github.com/T2PeNBiX99wcoxKv3A4g/Kotlin-Simple-Event-Bus/commit/7b05a84a217f5b95c796578082a7786af67be24d))

## New Contributors ❤️

* @T2PeNBiX99wcoxKv3A4g made their first contribution

<!-- generated by git-cliff -->
