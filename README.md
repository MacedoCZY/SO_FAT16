# FAT16
Sistema fat16 em java.\
Detalhes do funcionamento da fat16 a baixo, especificações retiradas do site: OsDev
- bytes por setor = 512
- setores-reservados = 1
- número de setores por FAT (BR 22) = 155
- número de entradas (BR 17) = 512
- FAT2 é uma cópia da FAT1 logo os setores tem o mesmo tamanho

Cada entrada no diretório raiz tem 32 bytes dado o padrão 8.3
(512\*32)/512= 32 setores ou 512\*32=16384 deslocamento

Valor é armazenado na memória em little indian, portanto:\
00|02 --> se lê --> 02|00

LFN se estiver marcado com E5 ele será excluído\
1 - Atributo(11) = 0xF (LFN) pala para 21\
Cada linha no hexed tem 16 bytes logo uma entrada são 2 linhas

2 - Se atributo(11) != 0xF
   - Atributo = 10 (10 é diretório)
   - Nome = SUB____
   - Ext = ___
   - First cluster = 00|03
   - tamanho = 00|00 00|00

   - Atributo = 20 (20 é arquivo)
   - Nome = TESTE
   - Ext = TXT
   - First cluster = 00|38
   - TAM = 00|00 04|4F (1103) tem que ser 3 cluster pelo menos visto que cada um = >

O padrão da FAT é que se tem 00 não tem mais arquivos ali adiante e E5

Cada entrada ocupa 2 bytes

Apartir de FFF8 = Ultimo cluster\
0000 = Cluester livre

Na área de dados não existe cluster 0, começa apartir de 2\
Isso por causa que 0000 significa cluster livre

Dado = 175616\
O deslocamento do cluster da área de dados é sempre -2, visto que ele começa apartir\
Cluster(56) = 56 - 2 = 54\*512 = 27648+175616 = 203264\
57-2 = 55\*512 = 203776\
58-2 = 56\*512 = 204288\
tamanhoDoAqrquivo-parteInteira(tamanhoDoArquivo/TamanhoDoCluster)\*TamanhoDoCluster

- número total de setores = 9C|40 (40000)

encontrando o sub diretório 3-2 = 1\*512 = 512+175616

Nome = .\
Ext = ___\
Tipo = 10(dir)\
First Cluster = 00|03\
Tamanho = 00|00 00|00

Nome = ..\
Ext = ___\
Tipo = 10(dir)\
First Cluster = 00|00\
Tamanho = 00|00 00|00

Nome = UB2\
Ext = __\
Tipo = 10(dir)\
First Cluster = 00|3B\
Tamanho = 00|00 00|00

Nome = TESTE\
Ext = txt\
Tipo = 20(arquivo)\
First Cluster = 00|3C\
Tamanho = 00|00 04|4F

 3C = 60*2+512=632 00|3D\
 3D = 61*2+512=634 00|3E\
 3E = 62*2+512=636 FF|FF

 60-2=58\*512=29696+175616=205312


achando SUB2\
00|3B = 59-2 = 57\*512 = 29184

Nome = .\
Ext = __\
Tipo = 10(dir)\
First Cluster = 00|3B\
Tamanho = 00|00 00|00

Nome = ..\
Ext = __\
Tipo = 10(dir)\
First Cluster = 00|03\
Tamanho = 00|00 00|00

cluster -2 + inicio dos DADOS\
olhar que 512/32=16 para saber se está utilizando mais de um setor\
normalmente se pode percebeer que irá ocorrer quando exedem 16\
arquivos no diretório
