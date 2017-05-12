//----------------------------------------------------
//Copyright (C), 2016,  Kawakp.
//��Ȩ���� (C), 2016,   Kawakp.
//����ģ��:	APP modbusͨѶ��ģ��
//���ߣ�yjs
//�汾��V1.0.0
//��ʼ�汾������ڣ�2016-04-20
//�ļ�����: Modbus��վͨѶ�����
//����˵��: �����Ҫ���ӱ��ͨѶ����,ֻ��Ҫ����"gat_MdsHighCmd"��"gat_MdsLowCmd"������
//�޶���ʷ:
//2. ...
//1. ����: 2016-04-20
//   ����: yjs
//   �°汾��: V1.0.0
//   �޸�˵��: ԭʼ�汾 
//------------------------------------------------------
#include "RobotVar.h"
#include "MdsModlink.h"
#include "MdsParaCheck.h"
#include "UartHw.h"
#include "mdbus.h"


#if	(ENABLE_DEBUG)
#define _yPrintf		LOGE
#else
#define _yPrintf(...)
#endif

// ���֧��100��ͨѶ,��Ϊ���ٶȱ�֤,��ôα��ȿ�����10����
unsigned long gu32_MdsHcmdCnt;
struct tag_MdsModlinkCmd gat_MdsHighCmd[] = {
/*id        cmd        IsStop    MstDel   cnt    pname    opAdr     opCnt     save      */
{ USID,   RdMutiBit,	0,		   0,	  0,	 "Y",		0,		  256,		0 },   	// ��ȡ��λ��Y0��ʼ��100��λԪ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiBit,	0,		   0,	  0,	 "M",		0,	      1000,		0 },	// ��ȡ��λ��M0 ��ʼ��1000��λԪ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   WrMutiBit,    0,         1,     0,     "M",       0,        1000,     0 },	// д��M0 ��ʼ��1000��λԪ������λ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   WrMutiBit,	0,		   1,	  1,	 "M",		1000,	  1000,		0 },   	// д��M1000 ��ʼ��1000��λԪ������λ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   0,		 100,	   0 }, 	// ��ȡ��λ��D0 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		0,		100,	0 },   // д����λ��D0 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   100, 	 100,	   0 }, 	// ��ȡ��λ��D100 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		100,	100,	0 },   // д����λ��D100 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,    0,         0,     1,     "D",      200,      100,      0 },   	// ��ȡ��λ��D200 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		200,	100,	0 },   // д����λ��D200 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,    0,         0,     1,     "D",      300,      100,      0 },		// ��ȡ��λ��D300 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		300,	100,	0 },   // д����λ��D300 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ UNUSE,  0,            0,         0,     0,     "",        0,        0,        0 }    // ���һ������������
};


// ���֧��100��ͨѶ
unsigned long gu32_MdsLcmdCnt;
struct tag_MdsModlinkCmd gat_MdsLowCmd[] = {
/*id        cmd        IsStop   MstDel    cnt    pname    opAdr     opCnt    save */
{ USID,   RdMutiBit,    0,         0,     0,  	 "X",       0,        256,      0 },   	// ��ȡ��λ��X0 ��ʼ��100��λԪ��
{ USID,   WrMutiBit,    0,         1,     0,     "Y",       0,        256,      0 },   	// д��Y0 ��ʼ��100��λԪ������λ��
{ USID,   RdMutiBit,	0,		   0,	  0,	 "M",		1000,	  1000, 	0 },	// ��ȡ��λ��M1000 ��ʼ��1000��λԪ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   400, 	 100,	   0 },   	// ��ȡ��λ��D400 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		400,	100,	0 },   // д����λ��D400 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiReg,    0,         0,     1,     "D",      500,      100,      0 },   	// ��ȡ��λ��D500 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		500,	100,	0 },   // д����λ��D500 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,    0,         0,     1,     "D",      600,      100,      0 },   	// ��ȡ��λ��D600 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		600,	100,	0 },   // д����λ��D600 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   700, 	 100,	   0 },   	// ��ȡ��λ��D700 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		700,	100,	0 },   // д����λ��D700 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },

{ USID,   RdMutiReg,    0,         0,     1,     "D",      800,      100,      0 },   	// ��ȡ��λ��D800 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		800,	100,	0 },   // д����λ��D800 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,    0,         0,     1,     "D",      900,      100,      0 },   	// ��ȡ��λ��D900 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		900,	100,	0 },   // д����λ��D900 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   1000, 	 100,	   0 },   	// ��ȡ��λ��D1000 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1000,	100,	0 },   // д����λ��D1000 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   1100, 	 100,	   0 },   	// ��ȡ��λ��D1100 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1100,	100,	0 },   // д����λ��D1100 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,    0,         0,     1,     "D",      1200,     100,      0 },   	// ��ȡ��λ��D1200 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1200,	100,	0 },   // д����λ��D1200 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,    0,         0,     1,     "D",      1300,     100,      0 },   	// ��ȡ��λ��D1300 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1300,	100,	0 },   // д����λ��D1300 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   1400, 	 100,	   0 },   	// ��ȡ��λ��D1400 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1400,	100,	0 },   // д����λ��D1400 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   1500, 	 100,	   0 },   	// ��ȡ��λ��D1500 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1500,	100,	0 },   // д����λ��D1500 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,    0,         0,     1,     "D",      1600,     100,      0 },   	// ��ȡ��λ��D1600 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1600,	100,	0 },   // д����λ��D1600 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,    0,         0,     1,     "D",      1700,     100,      0 },   	// ��ȡ��λ��D1700 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1700,	100,	0 },   // д����λ��D1700 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   1800, 	 100,	   0 },   	// ��ȡ��λ��D1800 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1800,	100,	0 },   // д����λ��D1800 ��ʼ��100����Ԫ��
{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   1900, 	 100,	   0 },   	// ��ȡ��λ��D1900 ��ʼ��100����Ԫ��
{ USID,   WrMutiReg,	0,		   1,	  0,	 "D",		1900,	100,	0 },   // д����λ��D1900 ��ʼ��100����Ԫ��

{ USID,   RdMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   WrMutiReg,	0,		   0,	  1,	 "D",	   600,		 20,	   0 },
{ USID,   RdMutiReg,    0,         1,     1,     "SD",      0,     100,      0 },		// ��ȡ��λ��SD0 ��ʼ��100����Ԫ��,ֻ��ȡһ��
{ USID,   RdMutiReg,	0,		   1,	  1,	 "SM",		0,	   512, 	 0 },		// ��ȡ��λ��SM0 ��ʼ��512��λԪ��,ֻ��ȡһ��

{ UNUSE,  0,            0,         0,     0,     "",        0,        0,        0 }    // ���һ������������
};

struct tag_ModelinkRecord gat_ModelinkRecord[MAX_MODELINK_RECORD];
struct tag_ModlinkFlag gt_ModeLinkFlag;

unsigned long _yInitModlinkVar(void)
{
    struct tag_ModelinkRecord *precord;
    struct tag_ModlinkFlag *pflg;
    struct tag_MdsModlinkCmd *phcmd;
    unsigned long hcnt;
    struct tag_MdsModlinkCmd *plcmd;
    unsigned long lcnt;
    unsigned long i,j,k;
    
    phcmd = &gat_MdsHighCmd[0];
    hcnt = 0;
	gu32_MdsHcmdCnt = 0;
    while (1) {
        if(phcmd->id ==UNUSE) {
            break;
        }
		phcmd++;
        hcnt++;
		gu32_MdsHcmdCnt++;
    }
	
    plcmd = &gat_MdsLowCmd[0];
    lcnt = 0;
	gu32_MdsLcmdCnt = 0;
    while (1) {
        if(plcmd->id ==UNUSE) {
            break;
        }
		plcmd++;
        lcnt++;
		gu32_MdsLcmdCnt++;
    }
	
    precord = &gat_ModelinkRecord[0];
    pflg = &gt_ModeLinkFlag;
    INIT_LIST_HEAD(&pflg->head);
    pflg->pcur = MYINIT(0);
    pflg->fsm = FSM_NON_INIT;
    pflg->cnt = 0;
    pflg->MainSend = 0;

    pflg->pSdBuf = &ma2u8_SendBuf[0][0];
    pflg->sendLen = 0;
    pflg->pRcBuf = (unsigned char *)0;
    pflg->rcvLen = 0;
    pflg->RetryTick = 1;
    pflg->RunNxtList = 0;

    pflg->pause = 0;
    pflg->slaveCmd = 0;

    phcmd = &gat_MdsHighCmd[0];
    plcmd = &gat_MdsLowCmd[0];
    if((hcnt==0) &&(lcnt==0)) {
        pflg->fsm = FSM_EMPTY;
        return 0;
    }
    else if(hcnt ==lcnt) {
        j = 0;
        for(i=0; i<hcnt; i++) {
            if(j >=MAX_MODELINK_RECORD) {
                goto CLC_ERR;
            }
            precord[j].pvoid = phcmd;
            list_add_tail(&precord[j].list, &pflg->head);
            phcmd++;
            j++;

            if(j >=MAX_MODELINK_RECORD) {
                goto CLC_ERR;
            }
            precord[j].pvoid = plcmd;
            list_add_tail(&precord[j].list, &pflg->head);
            plcmd++;
            j++;
        }
    }
    else if(hcnt >lcnt) {
        j =0;
        for(i=0; i<hcnt; i++) {
            if(j >=MAX_MODELINK_RECORD) {
                goto CLC_ERR;
            }
            precord[j].pvoid = phcmd;
            list_add_tail(&precord[j].list, &pflg->head);
            phcmd++;
            j++;
            
            if(lcnt >0) {
                if(j >=MAX_MODELINK_RECORD) {
                    goto CLC_ERR;
                }
                precord[j].pvoid = plcmd;
                list_add_tail(&precord[j].list, &pflg->head);
                plcmd++;
                j++;
                lcnt--;
            }
        }
    }
    else {
        j = 0;
        k = hcnt;
        for(i=0; i<lcnt; i++) {
            if(hcnt !=0) {
                if(j >=MAX_MODELINK_RECORD) {
                    goto CLC_ERR;
                }
                precord[j].pvoid = phcmd;
                list_add_tail(&precord[j].list, &pflg->head);
                phcmd++;
                j++;
                k--;
                if(k ==0) {
                    k = hcnt;
                    phcmd = &gat_MdsHighCmd[0];
                }
            }

            if(j >=MAX_MODELINK_RECORD) {
                goto CLC_ERR;
            }
            precord[j].pvoid = plcmd;
            list_add_tail(&precord[j].list, &pflg->head);
            plcmd++;
            j++;
        }
    }

    pflg->fsm = FSM_COMPILE_OK;
    pflg->cnt = j;
	_yPrintf("WrMutiBit !!!!: gu32_MdsHcmdCnt=%d, gu32_MdsLcmdCnt=%d, pflg->cnt=%d", gu32_MdsHcmdCnt, gu32_MdsLcmdCnt, pflg->cnt);
    return 0;
    
    CLC_ERR:
    pflg->fsm = FSM_COMPILE_OVER;
    pflg->cnt = MAX_MODELINK_RECORD;
	_yPrintf("WrMutiBit !!!!: gu32_MdsHcmdCnt=%d, gu32_MdsLcmdCnt=%d, pflg->cnt=%d", gu32_MdsHcmdCnt, gu32_MdsLcmdCnt, pflg->cnt);
    return 1;
}

unsigned long _yPackedBzMdsDta(struct tag_MdsElemMap *pmap, struct tag_MdsModlinkCmd *pcmd)
{
    struct tag_ModlinkFlag *pflg;
    unsigned char *pu8Tmp;
	unsigned char *pu8TmpChg;
    unsigned short *pu16Tmp;
	unsigned short *pu16TmpChg;
    unsigned long *pu32Tmp;
    unsigned long i,j,k,m,n;
	unsigned short mstAdr;
    unsigned char tmpu8;

    pflg = &gt_ModeLinkFlag;
    pflg->sendLen = 0;
    pflg->pSdBuf[0] = pcmd->id;
    pflg->pSdBuf[1] = pcmd->cmd;
	mstAdr = pmap->nStAdr +pcmd->opAdr -pmap->stElem;
    pflg->pSdBuf[2] = mstAdr >>8;
    pflg->pSdBuf[3] = mstAdr &0xff;
    switch(pcmd->cmd) {
    case RdMutiBit:     // �������Ȧ        
    case RdMutiIn:      // ��ȡ�������        
    case RdMutiReg:     // ������Ĵ���
        pflg->pSdBuf[4] = pcmd->opCnt >>8;
        pflg->pSdBuf[5] = pcmd->opCnt &0xff;
        pflg->sendLen =_yMdsAddVerify(pflg->pSdBuf, 6);
        break;
        
    case WrSimpBit:     // д������Ȧ
    	if(_yPointEq(pmap->pElemAdr, pmap->pWtElmAdr)) {
			return 2;
		}    
    	if(pmap->pu8Chg[pcmd->opAdr]) {							// �����иı�
    		pmap->pu8Chg[pcmd->opAdr] = 0;
        	pu8Tmp = (unsigned char *)(pmap->pWtElmAdr);
	        if(pu8Tmp[pcmd->opAdr]) {
	            pflg->pSdBuf[4] = 0xff;
	            pflg->pSdBuf[5] = 0x00;
	        }
	        else {
	            pflg->pSdBuf[4] = 0x00;
	            pflg->pSdBuf[5] = 0x00;
	        }
	        pflg->sendLen =_yMdsAddVerify(pflg->pSdBuf, 6);			
		}
		else {													// ����û�иı�
			return 3;
		}

        break;
        
    case WrSimpReg:     // д�����Ĵ���
    	if(_yPointEq(pmap->pElemAdr, pmap->pWtElmAdr)) {
			return 2;
		}
		if(pmap->pu8Chg[pcmd->opAdr]) {							// �����иı�		
			pmap->pu8Chg[pcmd->opAdr] = 0;
			pu16Tmp = (unsigned short *)(pmap->pWtElmAdr);
			pflg->pSdBuf[4] = pu16Tmp[pcmd->opAdr] >>8;
			pflg->pSdBuf[5] = pu16Tmp[pcmd->opAdr] &0xff;
			pflg->sendLen =_yMdsAddVerify(pflg->pSdBuf, 6);		
		}
		else {
			return 3;
		}
        break;
        
    case WrMutiBit:     // д�����Ȧ
        if(pcmd->opCnt >1900) {
            return 1;
        }
		if(_yPointEq(pmap->pElemAdr, pmap->pWtElmAdr)) {
			return 2;
		}
        pflg->pSdBuf[4] = pcmd->opCnt >>8;
        pflg->pSdBuf[5] = pcmd->opCnt &0xff;
        pflg->pSdBuf[6] = pcmd->opCnt >>3;
        if(pcmd->opCnt &0x07) {
            pflg->pSdBuf[6] += 1;
        }
        pu8Tmp = (unsigned char *)(pmap->pElemAdr);
		pu8TmpChg = (unsigned char *)(pmap->pWtElmAdr);
        tmpu8 = 0;
        j = 0;
        k = 0;
		m = pcmd->opAdr;
		n = 0;
        for(i=0; i<pcmd->opCnt; i++) {
			if(pmap->pu8Chg[m]) {
				pmap->pu8Chg[m] = 0;
				n = 1;
				tmpu8 += (pu8TmpChg[m]<<j);
			}
			else {
				tmpu8 += (pu8Tmp[m]<<j);
			}
            j++;
			m++;
            if(j >=8) {
                j = 0;
                pflg->pSdBuf[7+k] = tmpu8;
                tmpu8 = 0;
                k++;
            }
        }
		if(n >0) {													// �����ݸı�
	        if(pcmd->opCnt &0x07) {
	            pflg->pSdBuf[7+k] = tmpu8;
	            k++;
	        }
	        pflg->sendLen =_yMdsAddVerify(pflg->pSdBuf, 7+k);		
		}
		else {

			return 3;
		}
        break;
        
    case WrMutiReg:     // д����Ĵ���
        if(pcmd->opCnt >120) {
            return 1;
        }
		if(_yPointEq(pmap->pElemAdr, pmap->pWtElmAdr)) {
			return 2;
		}
		if(pcmd->pname[1]!='\0') {
			_yPrintf("WrSimpBit WantSend %c%c%d Num=%d ok!\n", pcmd->pname[0], pcmd->pname[1], pcmd->opAdr, pcmd->opCnt);
		}
		else {
			_yPrintf("WrSimpBit WantSend %c%d Num=%d ok!\n", pcmd->pname[0], pcmd->opAdr, pcmd->opCnt);
		}		
        pu16Tmp = (unsigned short *)(pmap->pElemAdr);
		pu16TmpChg = (unsigned short *)(pmap->pWtElmAdr);
		m = pcmd->opAdr;
		n = 0;
        pflg->pSdBuf[4] = pcmd->opCnt >>8;
        pflg->pSdBuf[5] = pcmd->opCnt &0xff;
        pflg->pSdBuf[6] = pcmd->opCnt <<1;
		k = 0;
        for(i=0; i<pcmd->opCnt; i++) {
			if(pmap->pu8Chg[m]) {
				pmap->pu8Chg[m] = 0;
				n = 1;
				pflg->pSdBuf[7+k] = pu16TmpChg[m] >>8;
				
				k++;
				pflg->pSdBuf[7+k] = pu16TmpChg[m] >>0;
				k++;				
			}
			else {
				pflg->pSdBuf[7+k] = pu16Tmp[m] >>8;
				k++;
				pflg->pSdBuf[7+k] = pu16Tmp[m] >>0;
				k++;
			}
			m++;
        }
		if(n >0) {													// �����ݸı�
        	pflg->sendLen =_yMdsAddVerify(pflg->pSdBuf, 7+k);
			if(pcmd->pname[1]!='\0') {
				_yPrintf("WrSimpBit Send %c%c%d Num=%d ok!\n", pcmd->pname[0], pcmd->pname[1], pcmd->opAdr, pcmd->opCnt);
			}
			else {
				_yPrintf("WrSimpBit Send %c%d Num=%d ok!\n", pcmd->pname[0], pcmd->opAdr, pcmd->opCnt);
			}
		}
		else {
			return 3;
		}
        break;
    default:            // ����
        return 1;
    }
    return 0;
}

unsigned long _yRunModlinkModule(void)
{
    struct tag_UartDcb *pdcb;
    struct tag_ModlinkFlag *pflg;
    static struct tag_MdsModlinkCmd *pcmd;
    static struct tag_ModelinkRecord *precord;
    static struct tag_MdsElemMap *pmap;
	unsigned char *ptu8;
    unsigned long cnt;
    unsigned long i,j,k;
	static unsigned long cyclecnt =0;

    pdcb =&gat_UartDcb[0];
    if((pdcb->flag.isTxing) ||(pdcb->flag.isRxing)) {                           // ��վ���ڷ���,�������ڽ���
        return 0;
    }
    
    pflg = &gt_ModeLinkFlag;
    cnt = 0;
    RE_CHECK:
    switch(pflg->fsm) {
    case FSM_NON_INIT:          // û�г�ʼ��
    case FSM_EMPTY:             // ��ʼ����,�����λ��
        break;
        
    case FSM_COMPILE_OK:        // ������ȷ
    case FSM_COMPILE_OVER:
 	  	if(pflg->pause ==0) {   // û����ͣ
            if(cnt >=pflg->cnt) {                                               // ��������,�˳�
                break;
            }
            pflg->pcur = pflg->head.next;
            if(pflg->pcur ==(&pflg->head)) {                                     // ��,�˳�
                pflg->fsm = FSM_EMPTY;
                goto RE_CHECK;
            }

            list_del_init(pflg->pcur);
            precord = list_entry(pflg->pcur, struct tag_ModelinkRecord, list);
            pcmd = (struct tag_MdsModlinkCmd *)(precord->pvoid);
            if(pcmd->IsStop) {                                                  // �Ѿ�ֹͣʹ��,������һ��
                cnt++;
                list_add_tail(pflg->pcur, &pflg->head);
                goto RE_CHECK;
            }
			if((pcmd->MstDel) &&(pcmd->cnt==0)) {
                cnt++;
                list_add_tail(pflg->pcur, &pflg->head);
                goto RE_CHECK;				
			}
            list_add_tail(pflg->pcur, &pflg->head);
            if(pcmd->cmd >WrMutiReg)
                pflg->fsm = FSM_SEND_EXT;                                       // �л�״̬��
            else
                pflg->fsm = FSM_SEND_BZ;                                        // �л�״̬��
            goto RE_CHECK;
        }
        break;
        
    case FSM_SEND_BZ:															// �������,��������
    	ptu8 = (unsigned char *)(pcmd->pname);
        pmap = _yMgetMdsElmIfo(pcmd->cmd, ptu8, _yGetStrLen(pcmd->pname), pcmd->opAdr);
        if( !pmap ) {															// ���ݰ�����
            pflg->fsm = FSM_COMPILE_OK;
            break;
        }
		if( _yPackedBzMdsDta(pmap, pcmd) ){										// ������ݳ���
            pflg->fsm = FSM_COMPILE_OK;
            break;        
        }
		if(pflg->sendLen >0) {
			//LOGE ("Modelink send data %d\n",cyclecnt);
			cyclecnt++;
	        pdcb->pTxBuf = pflg->pSdBuf;
	        pdcb->TxCnt = pflg->sendLen;
	        pdcb->pfSendByte(pdcb);
	        pflg->MainSend = 1;
	        pflg->RetryTick = 1;
	        pflg->fsm = FSM_WAIT_REC;
		}
		else {
			pflg->fsm = FSM_RUN_NXT;
		}

        break;
    case FSM_SEND_EXT:          // �������,��������(��չ)
        break;

    case FSM_WAIT_REC:
        if(pflg->RunNxtList) {                                                  // ������һ��ִ��
            pflg->RunNxtList = 0;
			pflg->fsm = FSM_DELY_TO_SEND;
			pflg->dlyStTime = _yGetCurMsTime();
            goto RE_CHECK;
        }
        break;
		
	case FSM_DELY_TO_SEND:
		if(_yIs1msTimeout(pflg->dlyStTime, 0)) {
			pflg->fsm = FSM_RUN_NXT;
			goto RE_CHECK;
		}
		break;
		
    case FSM_RUN_NXT:
        if(cnt >=pflg->cnt) {                                               // ��������,�˳�
            break;
        }
        pflg->pcur = pflg->head.next;
        if(pflg->pcur ==(&pflg->head)) {                                     // ��,�˳�
            pflg->fsm = FSM_EMPTY;
            goto RE_CHECK;
        }

        list_del_init(pflg->pcur);
        precord = list_entry(pflg->pcur, struct tag_ModelinkRecord, list);
        pcmd = (struct tag_MdsModlinkCmd *)(precord->pvoid);
        if(pcmd->IsStop) {                                                  // �Ѿ�ֹͣʹ��,������һ��
            cnt++;
            list_add_tail(pflg->pcur, &pflg->head);
            goto RE_CHECK;
        }
		if((pcmd->MstDel) &&(pcmd->cnt==0)) {
            cnt++;
            list_add_tail(pflg->pcur, &pflg->head);
            goto RE_CHECK;				
		}		
        list_add_tail(pflg->pcur, &pflg->head);
        if(pcmd->cmd >WrMutiReg)
            pflg->fsm = FSM_SEND_EXT;                                       // �л�״̬��
        else
            pflg->fsm = FSM_SEND_BZ;                                        // �л�״̬��
        goto RE_CHECK;
        //break;
        
    default:
    	_yInitModlinkVar();
        break;
    }
    return 0;
}




